package views;

import Utils.driverHelper;
import core.DriverFactory;
import exceptions.ViewNotDisplayedException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.TimeoutException;

/**
 * Created by shambu on 23/11/16.
 */
public class RSBBaseView {
    protected RemoteWebDriver driver;
    protected String viewRef;
    protected String viewName;
    protected static int viewLoadTimeOut = 5;

    public RSBBaseView(String viewName, String viewRef){
        driver = DriverFactory.getDriver();
        System.out.println("Creating " + viewName + " Ref" +viewRef);
    }

    public RSBBaseView(String viewName, String viewRef,By elementToBeChecked, int timeOutInSeconds){

        this(viewName,viewRef);

        try {
            if (timeOutInSeconds == 0){
                driverHelper.waitForPageReady(driver,20);
            } else {
                driverHelper.waitForPageReady(driver,timeOutInSeconds);
            }
            if (driver.findElement(elementToBeChecked) == null){
                throw new ViewNotDisplayedException(this);
            }
        } catch (TimeoutException | NoSuchElementException ex) {
            throw new ViewNotDisplayedException(this,ex);
        }

    }

    public String getPageDescription() {
        String description = viewName + " (" + viewRef + ")";
        return description;
    }
}
