package com.endpoint.challenge.automation.configurations;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringConfig.class)
public class CucumberSpringConfiguration {}
