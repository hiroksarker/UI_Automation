package com.project.qa.ui.helpers;

import com.project.qa.ui.webdriver.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public interface WaitHelper {

    Logger LOGGER = LoggerFactory.getLogger(WaitHelper.class);

    /**
     * This is ImplicitWait method
     *
     * @param timeout
     * @param unit
     */
    default void setImplicitWait(WebDriver driver, long timeout, TimeUnit unit) {
        LOGGER.info("Implicit Wait has been set to: " + timeout);
        driver.manage().timeouts().implicitlyWait(timeout, unit);
    }

    /**
     * This will help us to get WebDriverWait object
     *
     * @param timeOutInSeconds
     * @param pollingEveryInMiliSec
     * @return
     */
    @Deprecated
    default WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.driver, timeOutInSeconds);
        wait.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(ElementNotVisibleException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchFrameException.class);
        return wait;
    }

    /**
     * This method will make sure element is visible
     *
     * @param element
     * @param timeOutInSeconds
     * @param pollingEveryInMiliSec
     */
    default void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
                                                      int pollingEveryInMiliSec) {
        LOGGER.info("waiting for : {} for : {} seconds", element.toString(), timeOutInSeconds);
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(ExpectedConditions.visibilityOf(element));
        LOGGER.info("element is visible now");
    }

    /**
     * This method will make sure element is visible
     * @param by
     * @param timeOutInSeconds
     * @param pollingEveryInMiliSec
     */
    default void WaitForElementVisibleWithPollingTime(By by, int timeOutInSeconds,
                                                      int pollingEveryInMiliSec) {
        LOGGER.info("waiting for : {} for {} seconds", by, timeOutInSeconds);
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LOGGER.info("element is visible now");
    }

    /**
     * This method will make sure elementToBeClickable
     *
     * @param element
     * @param timeOutInSeconds
     */
    default void WaitForElementClickable(WebElement element, int timeOutInSeconds, int pollingEveryMiliSec) {
        LOGGER.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryMiliSec);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        LOGGER.info("element is clickable now");
    }

    /**
     * This method will make sure that element is clickable
     * @param by
     * @param timeOutInSeconds
     * @param pollingEveryMiliSec
     */
    default void WaitForElementClickable(By by, int timeOutInSeconds, int pollingEveryMiliSec) {
        LOGGER.info("waiting for :" + by + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryMiliSec);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        LOGGER.info("element is clickable now");
    }

    /**
     * This method will make sure invisibilityOf element
     *
     * @param element
     * @param timeOutInSeconds
     * @return
     */
    default boolean waitForElementNotPresent(WebElement element, int timeOutInSeconds, int pollingEveryMilliSec) {
        LOGGER.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryMilliSec);
        boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
        LOGGER.info("element is invisibile now");
        return status;
    }

    /**
     * This method will make sure invisibility of element
     * @param by
     * @param timeOutInSeconds
     * @param pollingEveryMilliSec
     * @return
     */
    default boolean waitForElementNotPresent(By by, int timeOutInSeconds, int pollingEveryMilliSec) {
        LOGGER.info("waiting for :" + by + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryMilliSec);
        boolean status = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        LOGGER.info("element is invisibile now");
        return status;
    }

    /**
     * This method will wait for frameToBeAvailableAndSwitchToIt
     *
     * @param element
     * @param timeOutInSeconds
     */
    default void waitForframeToBeAvailableAndSwitchToIt(WebElement element, int timeOutInSeconds, int pollingEveryMiliSec) {
        LOGGER.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryMiliSec);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        LOGGER.info("frame is available and switched");
    }

    /**
     * This method will wait for frameToBeAvailableAndSwitchToIt
     * @param by
     * @param timeOutInSeconds
     * @param pollingEveryMiliSec
     */
    default void waitForframeToBeAvailableAndSwitchToIt(By by, int timeOutInSeconds, int pollingEveryMiliSec) {
        LOGGER.info("waiting for :" + by + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryMiliSec);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
        LOGGER.info("frame is available and switched");
    }

    /**
     * Method to wait for element
     * @param element
     * @param timeOutInSeconds
     * @param pollingEveryInMiliSec
     */
    default void waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec) {
        LOGGER.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        Wait<WebDriver> fwait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        fwait.until(ExpectedConditions.visibilityOf(element));
        LOGGER.info("element is visible now");
    }

    /**
     * Method to wait for element
     * @param by
     * @param timeOutInSeconds
     * @param pollingEveryInMiliSec
     */
    default void waitForElement(By by, int timeOutInSeconds, int pollingEveryInMiliSec) {
        LOGGER.info("waiting for :" + by + " for :" + timeOutInSeconds + " seconds");
        Wait<WebDriver> fwait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        fwait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LOGGER.info("element is visible now");
    }

    /**
     * Method to wait for page load
     * @param driver
     * @param timeout
     * @param unit
     */
    default void pageLoadTime(WebDriver driver, long timeout, TimeUnit unit) {
        LOGGER.info("waiting for page to load for : " + unit + " seconds");
        driver.manage().timeouts().pageLoadTimeout(timeout, unit);
        LOGGER.info("page is loaded");
    }

    /**
     * Method to wait for element attribute value is set to expected value
     * @param webElement
     * @param attributeName
     * @param attributeValue
     * @param timeOut
     * @param pollyingFrequency
     */
    default void waitForElementAttributeContains(WebElement webElement, String attributeName,
                                                 String attributeValue, int timeOut, int pollyingFrequency) {
        Wait<WebDriver> wait = getWait(timeOut, pollyingFrequency);
        LOGGER.info("wait for element {} attribute {} contains value {}", webElement.toString(), attributeName, attributeValue);
        wait.until(ExpectedConditions.attributeContains(webElement, attributeName, attributeValue));
    }

    /**
     * Method to wait for element attribute value is set to expected value
     * @param by
     * @param attributeName
     * @param attributeValue
     * @param timeOut
     * @param pollyingFrequency
     */
    default void waitForElementAttributeContains(By by, String attributeName,
                                                 String attributeValue, int timeOut, int pollyingFrequency) {
        Wait<WebDriver> wait = getWait(timeOut, pollyingFrequency);
        LOGGER.info("wait for element {} attribute {} contains value {}", by.toString(), attributeName, attributeValue);
        wait.until(ExpectedConditions.attributeContains(by, attributeName, attributeValue));
    }
}
