package core;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by shambu on 23/11/16.
 */
public class DriverFactory {
    private static DriverType _selectedDriver = DriverType.FIREFOX;
    private static RemoteWebDriver _driver = null;
    public static RemoteWebDriver getDriver() {

        DesiredCapabilities desiredCapabilities = _selectedDriver
                .getDesiredCapabilities();
        _driver = _selectedDriver.getDriverObject(desiredCapabilities);
        return _driver;
    }
}
