package core;

import org.openqa.selenium.chrome.ChromeOptions;
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
                return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }
    },
    CHROME {
        @Override
        public DesiredCapabilities getDesiredCapabilities() {

            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setPlatform(Platform.MAC);

            // Including a workaround for Chrome 51 update
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--no-sandbox");
            capabilities.setCapability(ChromeOptions.CAPABILITY, co);
            // capabilities.setCapability(
            // CapabilityType.VERSION,
            // Utils.getDeviceProp().getProperty(
            // DevicePropertyNames.BROWSER_VERSION));
            return capabilities;
        }

        @Override
        public RemoteWebDriver getDriverObject(DesiredCapabilities desiredCapabilities) {

            try {
                return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
