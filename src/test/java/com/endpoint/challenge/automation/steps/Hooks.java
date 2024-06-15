package com.endpoint.challenge.automation.steps;

import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Log4j2
public class Hooks implements En {

    @Autowired
    private BrowserSetup browserSetup;

    @Autowired
    private SharedVariables sharedVariables;
    @Autowired
    private SharedEntities sharedEntities;

    @Value("${spring.profiles.active}")
    private String activeProfile;


    private ThreadLocal<RemoteWebDriver> driver;

    public Hooks() {

        Before("@ui and not @servicetask and not @servicehealthcheck and not @ignore",(Scenario scenario)->{
            sharedVariables.setScenarioName(scenario.getName());
            log.info("Before SECTION for "+scenario.getName());
            driver = new ThreadLocal<>();
            browserSetup.setUpDriver(driver);
        });

        After("@ui and not @servicetask and not @servicehealthcheck and not @ignore",()->{
            if(driver.get() != null){
                log.info("Local Thread After : "+ Thread.currentThread().getId()+" "+driver.get().getSessionId());
                driver.get().quit();
                log.info("DRIVER QUIT");
            }
            else{
                log.info("QUIT");
            }
            driver.remove();
        });
    }
}
