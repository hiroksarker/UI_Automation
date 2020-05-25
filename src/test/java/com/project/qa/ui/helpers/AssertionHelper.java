package com.project.qa.ui.helpers;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface AssertionHelper {
    Logger LOGGER = LoggerFactory.getLogger(AssertionHelper.class);

    /**
     * Method to verify assert true condition
     *
     * @param condition
     * @param message
     */
    default void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(message, condition);
            LOGGER.info("assertTrue success for condition {}", condition);
        } catch (AssertionError assertionError) {
            LOGGER.error("assertTrue failure for condition {} with message {}", condition, message);
            Assert.fail("Test case failed: assertion failed:" + message);
        }
    }

    /**
     * Method to verify assert false condition
     *
     * @param condition
     * @param message
     */
    default void assertFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(message, condition);
            LOGGER.info("assertFalse success for condition {}", condition);
        } catch (AssertionError assertionError) {
            LOGGER.error("assertFalse failure for condition {} with message {}", condition, message);
            Assert.fail("Test case failed: assertion failed:" + message);
        }
    }

    /**
     * Method to verify assert Equals condition
     *
     * @param expected
     * @param actual
     * @param message
     */
    default void assertEquals(Object expected, Object actual, String message) {
        try {
            Assert.assertEquals(message, expected, actual);
            LOGGER.info("assertEquals success: expected {}, actual {}", expected, actual);
        } catch (AssertionError assertionError) {
            LOGGER.error("assertEquals failure: expected {}, actual {}, message {}", expected, actual, message);
        }
    }
}
