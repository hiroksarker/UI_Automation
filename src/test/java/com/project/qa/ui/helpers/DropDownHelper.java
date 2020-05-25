package com.project.qa.ui.helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public interface DropDownHelper {

    Logger LOGGER = LoggerFactory.getLogger(DropDownHelper.class);

    /**
     * Method to select option using value
     *
     * @param element
     * @param value
     */
    default void selectUsingValue(WebElement element, String value) {
        Select select = new Select(element);
        LOGGER.info("selectUsingValue and value is: " + value);
        select.selectByValue(value);
    }

    /**
     * Method to select option using index
     *
     * @param element
     * @param index
     */
    default void selectUsingIndex(WebElement element, int index) {
        Select select = new Select(element);
        LOGGER.info("selectUsingIndex and index is: " + index);
        select.selectByIndex(index);
    }

    /**
     * Method to select option using visible text
     *
     * @param element
     * @param visibleText
     */
    default void selectUsingVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        LOGGER.info("selectUsingVisibleText and visibleText is: " + visibleText);
        select.selectByVisibleText(visibleText);
    }

    /**
     * Method to de select option using value
     *
     * @param element
     * @param value
     */
    default void deSelectUsingValue(WebElement element, String value) {
        Select select = new Select(element);
        LOGGER.info("deSelectUsingValue and value is: " + value);
        select.deselectByValue(value);
    }

    /**
     * Method to de select option using index
     *
     * @param element
     * @param index
     */
    default void deSelectUsingIndex(WebElement element, int index) {
        Select select = new Select(element);
        LOGGER.info("deSelectUsingIndex and index is: " + index);
        select.deselectByIndex(index);
    }

    /**
     * Method to de select option using visible text
     *
     * @param element
     * @param visibleText
     */
    default void deSelectUsingVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        LOGGER.info("deselectByVisibleText and visibleText is: " + visibleText);
        select.deselectByVisibleText(visibleText);
    }

    /**
     * Method to get all select option list
     *
     * @param element
     * @return list of options
     */
    default List<String> getAllDropDownData(WebElement element) {
        Select select = new Select(element);
        List<WebElement> elementList = select.getOptions();
        List<String> valueList = new LinkedList<String>();
        for (WebElement ele : elementList) {
            LOGGER.info(ele.getText());
            valueList.add(ele.getText());
        }
        return valueList;
    }
}
