package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxSettings implements ISettings{

  @Override
  public AbstractDriverOptions settings(String... userArgs) {
    FirefoxOptions firefoxOptions = new FirefoxOptions();

    if (userArgs != null && userArgs.length > 0 && userArgs[0] != null) {
      firefoxOptions.addArguments(userArgs[0]);
    }


    return firefoxOptions;
  }
}
