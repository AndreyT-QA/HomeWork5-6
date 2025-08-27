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

public class WebDriverFactory {

  private String browser = System.getProperty("browser");

  public WebDriver create(String mode){
    switch (browser) {
      case "chrome": {
        WebDriverManager.chromedriver().setup();
        ISettings settings = new ChromeSettings();
        ChromeOptions options = (ChromeOptions) settings.settings(mode);
        if (mode != null) {
          options.addArguments(mode);
        }
        options.addArguments("--remote-allow-origins=*"); //добавлено, т.к. автотесты падали по ошибке
        return new ChromeDriver(options);
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
