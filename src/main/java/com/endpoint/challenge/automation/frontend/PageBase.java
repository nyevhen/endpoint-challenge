package com.endpoint.challenge.automation.frontend;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
public class PageBase {

    private Long timeout = Long.valueOf(60);
    protected ThreadLocal<RemoteWebDriver> driver;
    private WebDriverWait wait;
    private  ArrayList<Integer> result;

    public PageBase(ThreadLocal<RemoteWebDriver> driver) {
        this.driver = driver;
        log.info("Page constructor");
        log.info(driver.toString());
        PageFactory.initElements(driver.get(), this);
    }


    public void waitForPageLoad() {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(timeout));
        wait.until(driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
    }

    @SuppressWarnings("deprecation")
    public WebElement waitElementToBeClickable(WebElement element) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public void clickElementUsingJs(WebElement element) {
        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].click();", element);
    }

    public List<String> switchToWindow(Integer windowNumber){
        List<String> browserTabs = new ArrayList<>(driver.get().getWindowHandles());
        log.info(String.valueOf(browserTabs.size()));
        log.info(browserTabs.get(0));
        log.info(driver.get().getCurrentUrl());
        for (int i=0; i <= browserTabs.size() - 1; i++){
            driver.get().switchTo().window(browserTabs.get(windowNumber));
            log.info(driver.get().getWindowHandle());
            log.info(driver.get().getCurrentUrl());
        }

        driver.get().switchTo().window(browserTabs.get(windowNumber));
        return browserTabs;
    }

    public Long getScrollBarPosition() {
        return (Long) ((JavascriptExecutor) driver.get()).executeScript("return window.pageYOffset;");
    }

    public Long scrollToElement(WebElement element) {

        ((JavascriptExecutor) driver.get()).executeScript("window.scrollBy(0,"+(element.getLocation().getY()-result.get(0))+");");
        return getScrollBarPosition();
    }

    public String getPageTitle() {
        return driver.get().getTitle();
    }

    private String dateFormated() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);
    }

    public void goBack(){
        driver.get().navigate().back();
    }

    public void switchToIFrame(WebElement iFrame){
        driver.get().switchTo().frame(iFrame);
    }

    public void enterValueUsingJs(String value, WebElement element){
        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].setAttribute('value',arguments[1]);", element,value);
    }

    public void jsSelect(WebElement element, String value) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("const textToFind = '" + value + "';" +
                "const dd = arguments[0];" +
                "dd.selectedIndex = [...dd.options].findIndex (option => option.text === textToFind);", element);
    }
}
