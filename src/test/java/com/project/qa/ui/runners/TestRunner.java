package com.project.qa.ui.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.project.qa.ui.stepDefinitions"
        //tags = {"@gui"}
)
public class TestRunner {
}
