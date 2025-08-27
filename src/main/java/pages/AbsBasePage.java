package pages;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage  extends AbsCommon {

  private String baseurl = System.getProperty("base.url");
  private String path;

  public AbsBasePage(WebDriver driver, String path) {
    super(driver);
    this.path = path;
  }

  public void open() {
    driver.get(baseurl + path);
  }

}
