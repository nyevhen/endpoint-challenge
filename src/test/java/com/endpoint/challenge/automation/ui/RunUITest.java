package com.endpoint.challenge.automation.ui;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/ui",
        tags = "@ui and not @ignore",
        glue = "com.endpoint.challenge.automation.steps",
        plugin = {"html", "html:target/cucumber-reports/conyebn/RunUITest.html",
                "json:target/cucumber-reports/conyebn/RunUITest.json"}
)

public class RunUITest extends AbstractTestNGCucumberTests {
}
