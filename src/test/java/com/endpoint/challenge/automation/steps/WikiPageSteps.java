package com.endpoint.challenge.automation.steps;
import com.endpoint.challenge.automation.configurations.SpringConfig;
import com.endpoint.challenge.automation.frontend.wiki.WikiPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.cucumber.java8.En;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.testng.Assert.*;

@Log4j2
@SpringBootTest(classes = SpringConfig.class)
public class WikiPageSteps implements En{
    @Autowired
    private BrowserSetup browserSetup;

    @Autowired
    private SharedVariables sharedVariables;

    @Autowired
    public SharedEntities sharedEntities;

    @Value("${ui.wiki.base-url}")
    private String wikiBaseUrl;

    @Value("${ui.wiki.login-page}")
    private String wikiLoginPageUrl;

    @Value("${ui.wiki.watchlist-page}")
    private String wikiWLUrl;

    @Value("${auth.user-login}")
    private String wikiUser;

    @Value("${auth.user-pwd}")
    private String wikiPwd;

    public WikiPageSteps() {

        Given("^User opens wiki login page in \"([^\"]*)\"$", (String browser) -> {
            String url = wikiBaseUrl;
            // Creating threadlocal webdriver to be able to run tests in parallel
            ThreadLocal<RemoteWebDriver> driver = browserSetup.getDriver(browser, wikiBaseUrl);
            // Adding webdriver and articles list to shared variables, so they can be accessed from any class
            sharedVariables.setDriver(driver);
            sharedVariables.setArticleTitleList(new ArrayList<>());
            // To simplify e2e scenario coding se can share Pages through shared entities
            sharedEntities.setWikiPage(new WikiPage(sharedVariables.getDriver()));
            assertTrue(sharedEntities.getWikiPage().getWikiPageLogo().isDisplayed());
        });

        And("^User navigates to \"([^\"]*)\"$", (String pageUrl) -> {
            sharedVariables.getDriver().get().get(wikiBaseUrl + pageUrl);
            sharedEntities.getWikiPage().waitForPageLoad();
        });

        When("^User login wikipedia$", () -> {
            sharedVariables.getDriver().get().get(wikiBaseUrl + wikiLoginPageUrl);
            sharedEntities.getWikiPage().waitForPageLoad();
            sharedEntities.getWikiPage().getWikiLoginUserNameInput().sendKeys(wikiUser);
            sharedEntities.getWikiPage().getWikiLoginPwdInput().sendKeys(wikiPwd);
            sharedEntities.getWikiPage().getWikiLoginCTA().click();
        });

        And("^User adds page to watchlist$", () -> {
            // Adding article to watchlist
            sharedEntities.getWikiPage().getWikiPageAddToWL().click();
            // Adding article title to array list to validate articles present in watchlist later
            sharedVariables.getArticleTitleList()
                    .add(sharedEntities.getWikiPage().getWikiPageArticleHeader().getText());

            sharedEntities.getWikiPage().waitForPageLoad();
        });

        Then("^User finds all saved articles in watchlist$", () -> {
            sharedEntities.getWikiPage().waitForPageLoad();
            // Now iterating through the list of article titles we added to array list when we viewed them
            sharedVariables.getArticleTitleList().forEach( a -> {
                // Iterating through articles list taken from watchlist page comparing to names
                // we added to array list when we saved individual article
                assertTrue(sharedEntities.getWikiPage().getWikiWatchlistArlicleLabelsList()
                        .stream().map(WebElement::getText).anyMatch(wla -> wla.contains(a)));
            });
        });

        And("^User navigates to watchlist$", () -> {
            sharedVariables.getDriver().get().get(wikiBaseUrl + wikiWLUrl);
            sharedEntities.getWikiPage().waitForPageLoad();
        });

        And("^User removes \"([^\"]*)\" article from watchlist$", (String articleLabel) -> {
            // Let's save full list of articles we had initially in watchlist
            sharedVariables.setArticleTitleList(new ArrayList<>());
            sharedEntities.getWikiPage().getWikiWatchlistArlicleLabelsList().forEach(a -> {
                sharedVariables.getArticleTitleList()
                        .add(a.getText());
            });
            // Marking checkbox of the article we gonna remove from watchlist
            sharedEntities.getWikiPage().getWLElementCheckBoxByLabel(articleLabel).click();
            sharedEntities.getWikiPage().getWikiWatchlistRemoveCTA().click();
            sharedVariables.getDriver().get().get(wikiBaseUrl + wikiWLUrl);
            // Removing deleted article from array list
            sharedVariables.getArticleTitleList().remove(articleLabel);
            sharedEntities.getWikiPage().waitForPageLoad();
        });

        Then("^User finds all saved articles excluding \"([^\"]*)\"$", (String articleLabel) -> {
            // Iterating through the array list of articles we created when we land to watchlist in very beginning of the test
            sharedVariables.getArticleTitleList().forEach( a -> {
                // Comparing to what we have left in watchlist
                assertTrue(sharedEntities.getWikiPage().getWikiWatchlistArlicleLabelsList()
                        .stream().map(WebElement::getText).anyMatch(wla -> wla.contains(a)));
            });
            // Validating that desired article <articleLabel>, removed from watchlist.
            assertFalse(sharedEntities.getWikiPage().getWikiWatchlistArlicleLabelsList().contains(articleLabel));
        });

    }
}
