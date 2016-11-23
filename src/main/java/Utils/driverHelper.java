package Utils;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

import java.util.concurrent.TimeUnit;

/**
 * Created by shambu on 23/11/16.
 */
public class driverHelper {
    public static void waitForPageReady(RemoteWebDriver driver, int timeInSeconds) {
        Wait<WebDriver> wait = getFluent(driver, timeInSeconds);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    private static Wait<WebDriver> getFluent(RemoteWebDriver driver, int timeInSeconds) {
        return new FluentWait<WebDriver>(driver).withTimeout(timeInSeconds, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
    }


    public void waitForElementToBeVisible(RemoteWebDriver driver, By by, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


}
