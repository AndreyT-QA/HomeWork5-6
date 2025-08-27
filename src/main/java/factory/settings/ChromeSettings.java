package factory.settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeSettings implements ISettings {

  @Override
  public AbstractDriverOptions settings(String... userArgs) {
    ChromeOptions chromeOptions = new ChromeOptions();

    if (userArgs != null && userArgs.length > 0 && userArgs[0] != null) {
      chromeOptions.addArguments(userArgs[0]);
    }


    return chromeOptions;
  }
}
