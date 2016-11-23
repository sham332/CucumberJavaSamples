package core;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * Created by shambu on 23/11/16.
 */
public interface DriverInterface {

    public <T extends RemoteWebDriver> T getDriverObject(
            DesiredCapabilities desiredCapabilities);

    public DesiredCapabilities getDesiredCapabilities();
}
