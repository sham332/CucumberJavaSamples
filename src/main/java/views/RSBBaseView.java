package views;

import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by shambu on 23/11/16.
 */
public class RSBBaseView {
    protected RemoteWebDriver driver;

    public RSBBaseView(String viewName, String viewRef){
        driver = DriverFactory.getDriver();
        System.out.println("Creating " + viewName + " Ref" +viewRef);
    }

    public RSBBaseView(String viewName, String viewRef,By elementToBeChecked, int timeOutInSeconds){

        this(viewName,viewRef);

    }
}
