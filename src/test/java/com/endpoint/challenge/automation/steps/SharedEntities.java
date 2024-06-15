package com.endpoint.challenge.automation.steps;

import com.endpoint.challenge.automation.frontend.wiki.WikiPage;
import com.endpoint.challenge.automation.frontend.PageBase;
import io.cucumber.spring.CucumberTestContext;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
@Data
public class SharedEntities {
    private String scenarioName;
    public PageBase pageBase;
    public WikiPage wikiPage;
}