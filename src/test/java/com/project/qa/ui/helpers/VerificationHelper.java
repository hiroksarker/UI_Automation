package com.project.qa.ui.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface VerificationHelper {

    Logger LOGGER = LoggerFactory.getLogger(VerificationHelper.class);

    /**
     * Method to check if element is displayed or not
     * @param element
     * @return true if displayed false otherwise
     */
    default boolean isDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            LOGGER.info("element is Displayed.." + element.getText());
            return true;
        } catch (Exception e) {
            LOGGER.error("element is not Displayed..", e.getCause());
            return false;
        }
    }

    /**
     * Method to check if element is not displayed
     * @param element
     * @return true if not displayed false otherwise
     */
    default boolean isNotDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            LOGGER.info("element is present.." + element.getText());
            return false;
        } catch (Exception e) {
            LOGGER.error("element is not present..");
            return true;
        }
    }

    /**
     * Method to read value from element
     * @param element
     * @return value
     */
    default String readValueFromElement(WebElement element) {
        if (null == element) {
            LOGGER.info("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed(element);
        if (status) {
            LOGGER.info("element text is .." + element.getText());
            return element.getText();
        } else {
            return null;
        }
    }

    /**
     * Method to get element text
     * @param element
     * @return element text
     */
    default String getText(WebElement element) {
        if (null == element) {
            LOGGER.info("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed(element);
        if (status) {
            LOGGER.info("element text is .." + element.getText());
            return element.getText();
        } else {
            return null;
        }
    }

    /**
     * Method to get brower title
     * @param driver
     * @return browser title
     */
    default String getTitle(WebDriver driver) {
        LOGGER.info("page title is: " + driver.getTitle());
        return driver.getTitle();
    }
}
