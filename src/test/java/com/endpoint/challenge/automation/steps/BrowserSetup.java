package com.endpoint.challenge.automation.steps;

import io.cucumber.java8.En;
import io.cucumber.spring.CucumberTestContext;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import java.net.MalformedURLException;
import java.time.Duration;


@Log4j2
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class BrowserSetup implements En {

    @Autowired
    private SharedVariables sharedVariables;

    private String extension="";

    @Value("${po.username}")
    private String cbtUserName;

    @Value("${po.authkey}")
    private String cbtAuthKey;

    @Value("${ui.hub-address}")
    private String seleniumHubAddress;

    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    /**
     * Returns threadlocal webdriver
     * @param browser
     * @param url
     * @return
     * @throws MalformedURLException
     */
    public ThreadLocal<RemoteWebDriver> getDriver(String browser,String url) throws MalformedURLException {
//        sharedVariables.setRemotePlatformExecution(false);
        log.info(browser);
        sharedVariables.setBrowserName(browser);

        switch (browser)
        {
            case "local_chrome" :
                ChromeOptions options = new ChromeOptions();
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver" + extension);
                options.addArguments("--disable-impl-side-painting");
                options.addArguments("--auto-open-devtools-for-tabs");
                options.addArguments("--remote-allow-origins=*");
//                options.addArguments("--headless");

                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                driver.set(new ChromeDriver(options));
                driver.get().manage().window().maximize();
                break;
        }

        sharedVariables.setSessionId(driver.get().getSessionId().toString());
        log.info(sharedVariables.getSessionId());
        driver.get().manage().timeouts().implicitlyWait(Duration.ofMinutes(30));
        driver.get().get(url);
        return  driver;

    }

    public void setUpDriver(ThreadLocal<RemoteWebDriver> driver)
    {
        this.driver=driver;
    }
}
