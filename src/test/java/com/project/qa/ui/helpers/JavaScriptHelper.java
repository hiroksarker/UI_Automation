package com.project.qa.ui.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface JavaScriptHelper {

    Logger LOGGER = LoggerFactory.getLogger(JavaScriptHelper.class);

    /**
     * This method will execute java script
     *
     * @param script
     * @return
     */
    default Object executeScript(WebDriver driver, String script) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        return exe.executeScript(script);
    }

    /**
     * This method will execute Java script with multiple arguments
     *
     * @param script
     * @param args
     * @return
     */
    default Object executeScript(WebDriver driver, String script, Object... args) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        return exe.executeScript(script, args);
    }

    /**
     * This method will scroll till element
     *
     * @param element
     */
    default void scrollToElement(WebDriver driver, WebElement element) {
        LOGGER.info("scroll to WebElement...");
        executeScript(driver, "window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
    }

    /**
     * Scroll till element and click
     *
     * @param element
     */
    default void scrollToElementAndClick(WebDriver driver, WebElement element) {
        scrollToElement(driver, element);
        element.click();
        LOGGER.info("element is clicked: " + element.toString());
    }

    /**
     * Scroll till element view
     *
     * @param element
     */
    default void scrollIntoView(WebDriver driver, WebElement element) {
        LOGGER.info("scroll till web element");
        executeScript(driver, "arguments[0].scrollIntoView()", element);
    }

    /**
     * Scroll till element view and click
     *
     * @param element
     */
    default void scrollIntoViewAndClick(WebDriver driver, WebElement element) {
        scrollIntoView(driver, element);
        element.click();
        LOGGER.info("element is clicked: " + element.toString());
    }

    /**
     * This method will scroll down vertically
     */
    default void scrollDownVertically(WebDriver driver) {
        LOGGER.info("scrolling down vertically...");
        executeScript(driver, "window.scrollTo(0,document.body.scrollHeight)");
    }

    /**
     * This method will scroll up vertically
     */
    default void scrollUpVertically(WebDriver driver) {
        LOGGER.info("scrolling up vertically...");
        executeScript(driver, "window.scrollTo(0,-document.body.scrollHeight)");
    }

    /**
     * This method will scroll till given pixel (e.g=1500)
     *
     * @param pixel
     */
    default void scrollDownByPixel(WebDriver driver, int pixel) {
        executeScript(driver, "window.scrollBY(0," + pixel + ")");
    }

    default void scrollUpByPixel(WebDriver driver, int pixel) {
        executeScript(driver, "window.scrollBY(0,-" + pixel + ")");
    }

    /**
     * This method will zoom screen by 100%
     */
    default void zoomInBy100Percentage(WebDriver driver) {
        executeScript(driver, "document.body.style.zoom='100%'");
    }

    /**
     * This method will zoom screen by 60%
     */
    default void zoomInBy60Percentage(WebDriver driver) {
        executeScript(driver, "document.body.style.zoom='40%'");
    }

    /**
     * This method will click on element
     *
     * @param element
     */
    default void clickElement(WebDriver driver, WebElement element) {
        executeScript(driver, "arguments[0].click();", element);
    }


}
