package com.endpoint.challenge.automation.frontend.wiki;

import com.endpoint.challenge.automation.frontend.PageBase;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Log4j2
@Getter
public class WikiPage extends PageBase {
    public WikiPage(ThreadLocal<RemoteWebDriver> driver) {
        super(driver);
    }

    /**
     * Returns wikipedia logo (top left corner of the page)
     */
    @FindBy(css = "[class*='mw-logo-wordmark']")
    private WebElement wikiPageLogo;

    /**
     * Username input at login form
     */
    @FindBy(css = "[id='wpName1']")
    private WebElement wikiLoginUserNameInput;

    /**
     * Password input at login form
     */
    @FindBy(css = "[id='wpPassword1']")
    private WebElement wikiLoginPwdInput;

    /**
     * Login CTA
     */
    @FindBy(css = "[id='wpLoginAttempt']")
    private WebElement wikiLoginCTA;

    /**
     * Returns watchlist tab
     */
    @FindBy(css = "[id='ca-special-specialAssociatedNavigationLinks-link-1']")
    private WebElement wikiWatchlistTab;

    /**
     * Returns list of web elements representing the article names at watchlist page
     */
    @FindBy(xpath = "//*[.='(Article)']//..//..//*[contains(@class,'oo-ui-checkboxMultioptionWidget')]//span[contains(@class, 'oo-ui-labelElement-label')]/a")
    public List<WebElement> wikiWatchlistArlicleLabelsList;

    /**
     * Remove from watchlist CTA
     */
    @FindBy(css = "[type='submit']")
    private WebElement wikiWatchlistRemoveCTA;

    /**
     * Article title
     */
    @FindBy(css = "[id='firstHeading']")
    private WebElement wikiPageArticleHeader;

    /**
     * Add to watchlist button styled as the star
     */
    @FindBy(css = "[id='ca-watch']")
    private WebElement wikiPageAddToWL;

    /**
     * Remove from watchlist button styled as the star
     */
    @FindBy(css = "[id='ca-unwatch']")
    private WebElement wikiPageRemoveFromWL;

    /**
     * Check all articles in watchlist
     */
    @FindBy(css = "[name='wpCheckAllNs0']")
    private WebElement wikiPageCheckAllInWL;


    /**
     * Method can be used to get web element representing the checkbox of a certain article
     *
     * @param label the title of the article
     * @return checkbox web element
     */
    public WebElement getWLElementCheckBoxByLabel(String label) {
        return driver.get()
                .findElement(
                        By.xpath("//*[.='" + label + "']//..//..//*[contains(@class,'oo-ui-inputWidget-input')]"));
    }

}
