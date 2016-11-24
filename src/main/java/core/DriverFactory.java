package core;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by shambu on 23/11/16.
 */
public class DriverFactory {
    private static DriverType _selectedDriver = DriverType.CHROME;
    private static RemoteWebDriver _driver = null;
    public static RemoteWebDriver getDriver() {

        if(_driver ==null) {
            DesiredCapabilities desiredCapabilities = _selectedDriver
                    .getDesiredCapabilities();
            _driver = _selectedDriver.getDriverObject(desiredCapabilities);

        }
        return _driver;
    }

    public static void  setDriver(String reset){
        if(reset==null){
            _driver.quit();
            _driver = null;
        }
    }
}
