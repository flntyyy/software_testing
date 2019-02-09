
package ru.alksndr.software.testing.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class ApplicationManager {
    private final Properties properties;
    WebDriver driver;
    private String browser;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("targer", "local");

        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (browser.equals(BrowserType.GOOGLECHROME)) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
            driver = new ChromeDriver();
        } else if (browser.equals(FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
            driver = new FirefoxDriver();
        } else if (browser.equals(BrowserType.SAFARI)) {
            driver = new SafariDriver();
        }


        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(properties.getProperty("web.baseUrl"));

    }

    public void stop() {
        driver.quit();
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
