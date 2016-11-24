package views;

import Utils.driverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shambu on 23/11/16.
 */
public class Home extends RSBBaseView {
    private static By brandLabelLocator = By.cssSelector(".navbar-brand");
    private List<WebElement> navBarLinks = new ArrayList<WebElement>();
    public LoginModal loginModal = new LoginModal();
    public Home() {
        super("Home", "01", brandLabelLocator , 5);
        navBarLinks = driver.findElements(By.cssSelector(".navbar-nav a"));
    }

    public void openLogin(){
        System.out.println("Clicking on Login");
        driverHelper.getElementByText(navBarLinks,"Login").click();
        System.out.println("Waiting for Login pop up");
        driverHelper.waitForElementToBeVisible(driver,By.cssSelector(".modal-title"),5);
        loginModal.setLoginModal(driver.findElement(By.cssSelector(".modal-dialog")));
    }

    public class LoginModal{
        private WebElement loginModal = null;
        public void setLoginModal(WebElement loginModal){
            this.loginModal = loginModal;
        }
        public void enterUserName(String userName){
            System.out.println("Logging in with UserName " +  userName);
            loginModal.findElement(By.cssSelector("#username")).sendKeys(userName);
        }

        public void enterPassword(String password){
            System.out.println("Logging in with Password " +  password);
            loginModal.findElement(By.cssSelector("#password")).sendKeys(password);
        }

        public void clickLogin(){
            System.out.println("Clicking on Login button ");
            loginModal.findElement(By.cssSelector("#doLogin")).click();
        }
    }







}
