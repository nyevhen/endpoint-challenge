package com.endpoint.challenge.automation.steps;

import io.cucumber.spring.CucumberTestContext;
import lombok.Data;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
@Data
public class SharedVariables {
    private String scenarioName;
    private Integer testId;
    private String sessionId;
    private String browserName;
    private String articleLabel;
    private ThreadLocal<RemoteWebDriver> driver;
    public List<String> articleTitleList;



    public String getCurrentUrl() {
        return driver.get().getCurrentUrl();
    }
}
