package com.project.qa.ui.webdriver;

import com.project.qa.ui.enums.DriverType;
import com.project.qa.ui.enums.EnvironmentType;
import com.project.qa.ui.readers.ConfigFileReader;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class WebDriverManager {
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverManager.class);
    public static EventFiringWebDriver driver;
    public static WebDriver webDriver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    EventListeners eventListeners;

    public WebDriverManager() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        driverType = configFileReader.getBrowser();
        environmentType = configFileReader.getEnvironment();
    }

    /**
     * Method to get driver instance
     * @return driver instance
     */
    public EventFiringWebDriver getDriver() {
        if (driver == null) driver = createDriver();
        return driver;
    }

    /**
     * Method to create driver instance
     * @return driver instance
     */
    private EventFiringWebDriver createDriver() {
        switch (environmentType) {
            case LOCAL:
                driver = createLocalDriver();
                break;
            case REMOTE:
                driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    /**
     * Method to create remote driver instance
     * @return remote driver instance
     */
    private EventFiringWebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    /**
     * Method to create local driver instance
     * @return local driver instance
     */
    private EventFiringWebDriver createLocalDriver() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        LOGGER.info("creating web driver: {}", driverType);
        switch (driverType) {
            case FIREFOX:
                webDriver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty(CHROME_DRIVER_PROPERTY, configFileReader.getDriverPath());
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("useAutomationExtension", false);
                options.addArguments("--lang=en-ca");
                webDriver = new ChromeDriver(options);
                break;
            case INTERNETEXPLORER:
                webDriver = new InternetExplorerDriver();
                break;
        }
        LOGGER.info("maximizing web browser window");
        if (configFileReader.getBrowserWindowSize()) webDriver.manage().window().maximize();
        LOGGER.info("web browser implicit wait is set to: {}", configFileReader.getImplicitlyWait());
        webDriver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
        driver = new EventFiringWebDriver(webDriver);
        eventListeners = new EventListeners();
        LOGGER.info("registering event listeners to web browser");
        driver.register(eventListeners);
        return driver;
    }

    /**
     * Method to close driver instance
     */
    public void closeDriver() {
        LOGGER.info("closing web browser");
        driver.close();
        driver.quit();
        LOGGER.info("unregistering event listeners from web browser");
        driver.unregister(eventListeners);
        driver = null;
    }

    /**
     * Method to capture browser screenshot
     * @param fileName
     */
    public void captureScreenshot(String fileName) {
        LOGGER.info("capturing web browser screenshot in file: {}", fileName);
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String testName = fileName.replace(" ", "_").concat(".png");
            FileUtils.copyFile(screenshot, new File(new ConfigFileReader().getScreenshotPath() + testName));
        } catch (WebDriverException e) {
            LOGGER.error("error while capturing screenshot: {}", e.getMessage());
        } catch (ClassCastException e) {
            LOGGER.error("error while capturing screenshot: {}", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("error while capturing screenshot: {}", e.getMessage());
        }
    }
}
