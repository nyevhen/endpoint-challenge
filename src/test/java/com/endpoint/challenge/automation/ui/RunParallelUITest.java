//package com.endpoint.challenge.automation.ui;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//import org.testng.annotations.DataProvider;
//
//@CucumberOptions(
//        features = "src/test/resources/features/ui",
//        tags = "@ui and not @ignore",
//        glue = "com.endpoint.challenge.automation.steps",
//        plugin = {"html", "html:target/cucumber-reports/conyebn/RunParallelUITest.html",
//        "json:target/cucumber-reports/conyebn/RunParallelUITest.json"}
//)
//public class RunParallelUITest extends AbstractTestNGCucumberTests {
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
//}
