package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbsBasePage;
import waiters.Waiter;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public abstract class AbsCommon {

  protected WebDriver driver;
  protected Actions actions;
  protected Waiter wait;
  protected final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
  protected static final Logger logger = LogManager.getLogger(AbsCommon.class);

  public AbsCommon(WebDriver driver) {
    this.driver = driver;
    this.actions = new Actions(driver);
    this.wait = new Waiter(driver);
  }

  protected WebElement $(By locator) {
    wait.waitForElementPresent(locator);
    return driver.findElement(locator);
  }

  protected List<WebElement> $$(By locator) {
    wait.waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
    return driver.findElements(locator);
  }

  protected void click(By locator) {
    $(locator).click();
  }

  protected void type(By locator, String text) {
    WebElement element = $(locator);
    element.clear();
    element.sendKeys(text);
  }
}
