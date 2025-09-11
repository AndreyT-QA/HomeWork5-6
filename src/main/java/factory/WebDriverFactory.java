package factory;

import exceptions.BrowserNotSupportedExceptions;
import factory.settings.ChromeSettings;
import factory.settings.FirefoxSettings;
import factory.settings.ISettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.URL;

public class WebDriverFactory {

  private String browser = System.getProperty("browser");

  public WebDriver create(String mode) {
    switch (browser) {
      case "chrome": {
        WebDriverManager.chromedriver().setup();
        ISettings settings = new ChromeSettings();
        ChromeOptions options = (ChromeOptions) settings.settings(mode);
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
          /* How to add test badge */
          put("name", "Test badge...");
          /* How to set session timeout */
          put("sessionTimeout", "10m");
          /* How to set timezone */
          put("env", new ArrayList<String>() {{
            add("TZ=UTC");
          }});
          put("labels", new HashMap<String, Object>() {{
            put("manual", "true");
          }});

        }});
        options.setCapability("browserName", "chrome");
        options.setCapability("browserVersion", "latest");

//        try {
//          remoteWebDriver = new RemoteWebDriver(new URL("http://193.104.57.173/wd/hub"), options);
//        } catch (MalformedURLException e) {
//          throw new RuntimeException(e);
//        }
        if (mode != null) {
          options.addArguments(mode);
        }
        options.addArguments("--remote-allow-origins=*"); //добавлено, т.к. автотесты падали по ошибке

        try {
          System.out.println("Попытка подключения к Selenoid...");
          return new RemoteWebDriver(new URL("http://193.104.57.173:4444/wd/hub"), options);
        } catch (Exception e) {
          System.out.println("Подключение к Selenoid не выполнено, используем ChromeDriver: " + e.getMessage());
          return new ChromeDriver(options);
        }

//        if (mode != null) {
//          options.addArguments(mode);
//        }
//        options.addArguments("--remote-allow-origins=*"); //добавлено, т.к. автотесты падали по ошибке
//        return remoteWebDriver;
      }
      case "firefox": {
        WebDriverManager.firefoxdriver().setup();
        ISettings settings = new FirefoxSettings();
        return new FirefoxDriver((FirefoxOptions) settings.settings(mode));
      }
    }
    throw new BrowserNotSupportedExceptions(browser);
  }

}
