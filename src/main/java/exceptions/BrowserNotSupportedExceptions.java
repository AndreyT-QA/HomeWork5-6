package exceptions;

import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class BrowserNotSupportedExceptions extends RuntimeException {

  public BrowserNotSupportedExceptions(String browserName) {
    super(String.format("Browser %s not supported", browserName));
  }
}
