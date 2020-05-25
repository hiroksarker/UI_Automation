package com.project.qa.ui.helpers;

import com.project.qa.ui.webdriver.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface JQueryHelper {
    Logger LOGGER = LoggerFactory.getLogger(JQueryHelper.class);

    /**
     * Method to get element value by name
     * @param name
     * @return value
     */
    default String jsGetValueByName(String name) {
        new JSWaiter().waitAllRequest();
        LOGGER.info("fetching value for element with name: {}", name);
        String value = (String) ((JavascriptExecutor) WebDriverManager.driver).executeScript("return $(\"*[name=" + name + "]\").val();");
        return (value == null) ? null : value.trim();
    }

    /**
     * Method to get element value by name
     * @param webElement
     * @return value
     */
    default String jsGetValueByName(WebElement webElement) {
        String name = webElement.getAttribute("name");
        new JSWaiter().waitAllRequest();
        LOGGER.info("fetching value for element with name: {}", webElement.toString());
        String value = (String) ((JavascriptExecutor) WebDriverManager.driver).executeScript("return $(\"*[name=" + name + "]\").val();");
        return (value == null) ? null : value.trim();
    }

    /**
     * Method to set element value by name
     * @param name
     * @param value
     */
    default void jsSetValueByName(String name, String value) {
        new JSWaiter().waitAllRequest();
        LOGGER.info("setting value for element with name: {} with value {}", name, value);
        ((JavascriptExecutor) WebDriverManager.driver).executeScript("$(\"*[name=" + name + "]\").val(" + value + ");");
    }

    /**
     * Method to get element by attribute name
     * @param attributeName
     * @param attributeValue
     * @return value
     */
    default String jsGetValueBy(String attributeName, String attributeValue) {
        new JSWaiter().waitAllRequest();
        LOGGER.info("fetching value for element with attribute: {} with value {}", attributeName, attributeValue);
        String value = (String) ((JavascriptExecutor) WebDriverManager.driver).executeScript("return $(\"*[" + attributeName + "=" + attributeValue + "]\").val();", new Object[0]);
        return (value == null) ? null : value.trim();
    }
}
