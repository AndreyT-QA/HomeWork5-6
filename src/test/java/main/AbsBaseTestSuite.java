package main;

import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public abstract class AbsBaseTestSuite {

  protected WebDriver driver;
  protected String mode;

  @BeforeEach
  public void init() {
    this.driver = new WebDriverFactory().create(mode);
  }


  @AfterEach
  public void close() {
    if(driver != null) {
      driver.quit();
    }
  }
}
