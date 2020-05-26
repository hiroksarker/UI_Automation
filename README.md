![Build and Test](https://github.com/hiroksarker/UI_Automation/workflows/Build%20and%20Test/badge.svg?branch=master)

# UI Automation
Selenium, Cucumber, JUnit

Selenium - https://www.selenium.dev/documentation/en/

Selenium is a portable framework for testing web applications. Selenium provides a playback tool for authoring functional tests without the need to learn a test scripting language

Cucumber - https://cucumber.io/

It’s simple. Whether open source or commercial, our collaboration tools will boost your engineering team's performance by employing Behavior-Driven Development (BDD). And with our world-class training, take it to places it’s never been. 

JUnit - https://junit.org/junit5/

JUnit 5 is the next generation of JUnit. The goal is to create an up-to-date foundation for developer-side testing on the JVM. This includes focusing on Java 8 and above, as well as enabling many different styles of testing.

# Pre-requisites
- <a href="https://java.com/en/download/manual.jsp" target="_blank">Java</a>
- <a href="https://maven.apache.org/download.cgi" target="_blank">Maven</a>
- <a href="https://www.jetbrains.com/idea/download/" target="_blank">IntelliJ</a>

Using tests in your project
----------------------------------

In your TestRunner class add a glue option:
```
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
```

Maven/Gradle Dependency
-----------------------

See https://jitpack.io/#selenium-cucumber/selenium-cucumber-java .

Ref: Vikas S.