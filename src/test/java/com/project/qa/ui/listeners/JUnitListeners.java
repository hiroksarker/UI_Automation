package com.project.qa.ui.listeners;

import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JUnitListeners extends RunListener {
    Logger LOGGER = LoggerFactory.getLogger(JUnitListeners.class);
    long startTime;
    long endTime;

    /**
     * This method is executed when test run started
     *
     * @param description
     * @throws Exception
     */
    @Override
    public void testRunStarted(Description description) throws Exception {
        startTime = new Date().getTime();
        LOGGER.info("Tests started! Number of Test case: {}", description.testCount());
    }

    /**
     * This method is executed when test run finished
     *
     * @param result
     * @throws Exception
     */
    @Override
    public void testRunFinished(Result result) throws Exception {
        endTime = new Date().getTime();
        LOGGER.info("Tests finished! Number of test case: {}", result.getRunCount());
        long elapsedSeconds = (endTime - startTime) / 1000;
        LOGGER.info("Elapsed time of tests execution: {} seconds", elapsedSeconds);
    }

    /**
     * This method is executed with test case started
     *
     * @param description
     * @throws Exception
     */
    @Override
    public void testStarted(Description description) throws Exception {
        LOGGER.info("{} test is starting...", description.getMethodName());
    }

    /**
     * This method is executed when test is finished
     *
     * @param description
     * @throws Exception
     */
    @Override
    public void testFinished(Description description) throws Exception {
        LOGGER.info("{} test is finished...", description.getMethodName());
    }

    /**
     * This method is executed on test case failure
     *
     * @param failure
     * @throws Exception
     */
    @Override
    public void testFailure(Failure failure) throws Exception {
        LOGGER.error("{} test FAILED!!!", failure.getDescription().getMethodName());
    }

    /**
     * This method is executed when test case is not executed due to some issue
     *
     * @param description
     * @throws Exception
     */
    @Override
    public void testIgnored(Description description) throws Exception {
        super.testIgnored(description);
        Ignore ignore = description.getAnnotation(Ignore.class);
        String ignoreMessage = String.format(
                "@Ignore test method '%s()': '%s'",
                description.getMethodName(), ignore.value());
        LOGGER.warn(ignoreMessage);
    }
}
