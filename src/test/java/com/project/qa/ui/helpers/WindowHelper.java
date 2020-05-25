package com.project.qa.ui.helpers;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;

import static com.project.qa.ui.webdriver.WebDriverManager.driver;

public interface WindowHelper {

    Logger LOGGER = LoggerFactory.getLogger(WindowHelper.class);

    /**
     * This method will switch to parent window
     */
    default void switchToParentWindow(WebDriver driver) {
        LOGGER.info("switching to parent window...");
        driver.switchTo().defaultContent();
    }

    /**
     * This method will switch to child window based on index
     *
     * @param index
     */
    default void switchToWindow(int index) {
        Set<String> windows = driver.getWindowHandles();
        int i = 1;
        for (String window : windows) {
            if (i == index) {
                LOGGER.info("switched to : " + index + " window");
                driver.switchTo().window(window);
            } else {
                i++;
            }
        }
    }

    /**
     * This method will close all tabbed window and
     * switched to main window
     */
    default void closeAllTabsAndSwitchToMainWindow(WebDriver driver) {
        Set<String> windows = driver.getWindowHandles();
        String mainwindow = driver.getWindowHandle();

        for (String window : windows) {
            if (!window.equalsIgnoreCase(mainwindow)) {
                driver.close();
            }
        }
        LOGGER.info("switched to main window");
        driver.switchTo().window(mainwindow);
    }

    /**
     * This method will do browser back navigation
     */
    default void navigateBack(WebDriver driver) {
        LOGGER.info("navigating back");
        driver.navigate().back();
    }

    /**
     * This method will do browser forward navigation
     */
    default void navigateForward(WebDriver driver) {
        LOGGER.info("navigating forward");
        driver.navigate().forward();
    }
}
