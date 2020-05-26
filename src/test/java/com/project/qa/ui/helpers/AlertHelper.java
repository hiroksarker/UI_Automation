package com.project.qa.ui.helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.project.qa.ui.webdriver.WebDriverManager.driver;

public interface AlertHelper {
    Logger LOGGER = LoggerFactory.getLogger(AlertHelper.class);

    /**
     * Method to switch to Javascript alert
     * @param driver
     * @return Alert
     */
    default Alert getAlert(WebDriver driver) {
        LOGGER.info("alert test: " + driver.switchTo().alert().getText());
        return driver.switchTo().alert();
    }

    /**
     * Method to accept Javascript alert
     * @param driver
     */
    default void acceptAlert(WebDriver driver) {
        getAlert(driver).accept();
        LOGGER.info("accept Alert is done...");
    }

    /**
     * Method to dismiss Javascript alert
     * @param driver
     */
    default void dismissAlert(WebDriver driver) {
        getAlert(driver).dismiss();
        LOGGER.info("dismiss Alert is done...");
    }

    /**
     * Method to get Javascript alert text
     * @param driver
     * @return alert text
     */
    default String getAlertText(WebDriver driver) {
        String text = getAlert(driver).getText();
        LOGGER.info("alert text: " + text);
        return text;
    }

    /**
     * Method to check if Javascript alert is present or not
     * @param driver
     * @return true if alert present false otherwise
     */
    default boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            LOGGER.info("alert is present");
            return true;
        } catch (NoAlertPresentException e) {
            LOGGER.info(String.valueOf(e.getCause()));
            return false;
        }
    }

    /**
     * Method to accept Javascript alert if present
     * @param driver
     */
    default void acceptAlertIfPresent(WebDriver driver) {
        if (isAlertPresent(driver)) {
            acceptAlert(driver);
        } else {
            LOGGER.info("Alert is not present..");
        }
    }

    /**
     * Method to dismiss Javascript alert if present
     * @param driver
     */
    default void dismissAlertIfPresent(WebDriver driver) {
        if (isAlertPresent(driver)) {
            dismissAlert(driver);
        } else {
            LOGGER.info("Alert is not present..");
        }
    }

    /**
     * Method to accept Javascript alert
     * @param text
     */
    default void acceptPrompt(String text) {
        if (isAlertPresent(driver)) {
            Alert alert = getAlert(driver);
            alert.sendKeys(text);
            alert.accept();
            LOGGER.info("alert text: " + text);
        }
    }
}
