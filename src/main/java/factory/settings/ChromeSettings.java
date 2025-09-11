package factory.settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.URL;

public class ChromeSettings implements ISettings {

  @Override
  public AbstractDriverOptions settings(String... userArgs) {
    ChromeOptions chromeOptions = new ChromeOptions();
//    chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
//      /* How to add test badge */
//      put("name", "Test badge...");
//
//      /* How to set session timeout */
//      put("sessionTimeout", "10m");
//
//      /* How to set timezone */
//      put("env", new ArrayList<String>() {{
//        add("TZ=UTC");
//      }});

      /* How to add "trash" button */
//      put("labels", new HashMap<String, Object>() {{
//        put("manual", "true");
//      }});
//
//    }});
//    chromeOptions.setCapability("browserName", "Chrome");
//    chromeOptions.setCapability("browserVersion", "latest");
//
//    try {
//      RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL("http://193.104.57.173/wd/hub"), chromeOptions);
//    } catch (MalformedURLException e) {
//      throw new RuntimeException(e);
//    }

    if (userArgs != null && userArgs.length > 0 && userArgs[0] != null) {
      chromeOptions.addArguments(userArgs[0]);
    }

    return chromeOptions;
    }
}
