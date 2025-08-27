package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {
  private WebDriver driver;

  public Waiter (WebDriver driver) {
    this.driver = driver;
  }

  public boolean waitForCondition(ExpectedCondition condition) {
    try {
      new WebDriverWait(driver, Duration.ofSeconds(5)).until(condition);
      return true;
    } catch (TimeoutException ignored) {
      return false;
    }
    }
    public boolean waitForElementPresent(By locator) {
      return waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
    }
  }



