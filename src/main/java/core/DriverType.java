package core;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Platform;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by shambu on 23/11/16.
 */
public enum DriverType implements DriverInterface {
    FIREFOX {
        @Override
        public DesiredCapabilities getDesiredCapabilities() {

            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setPlatform(Platform.MAC);
            return capabilities;
        }

        @Override
        public RemoteWebDriver getDriverObject(DesiredCapabilities desiredCapabilities) {

            try {
                return new RemoteWebDriver(new URL("http://192.168.99.100:3003/"), desiredCapabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
