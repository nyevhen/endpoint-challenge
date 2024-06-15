package com.endpoint.challenge.automation.steps.configurations;

import com.endpoint.challenge.automation.configurations.SpringConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.spring.CucumberTestContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;

@Log4j2
@CucumberContextConfiguration
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
@SpringBootTest(classes = SpringConfig.class)
public class CucumberSpringConfiguration {

}
