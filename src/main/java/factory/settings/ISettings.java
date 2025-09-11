package factory.settings;

import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public interface ISettings {

  AbstractDriverOptions settings(String... userArgs);
}
