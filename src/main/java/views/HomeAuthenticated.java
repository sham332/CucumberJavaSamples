package views;

import Utils.driverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shambu on 23/11/16.
 */
public class HomeAuthenticated extends RSBBaseView {
    private By createButton = By.cssSelector("#createHotel");
    private By hotelNameInput = By.cssSelector("#hotelName");
    private By hotelAddressInput = By.cssSelector("#address");
    private By hotelOwnerInput = By.cssSelector("#owner");
    private By hotelPhoneInput = By.cssSelector("#phone");
    private By hotelEmailInput = By.cssSelector("#email");
    private By hotelRowIdentifier = By.cssSelector(".hotelRow");
    private ArrayList<HotelRow> hotels = null;

    public HomeAuthenticated() {
        super("Home Authenticated", "02", By.cssSelector("#logout"), RSBBaseView.viewLoadTimeOut);

    }

    public boolean clickOnCreateHotel(){
        System.out.println("Clicking on create button");
        int initialNo = getDisplayedHotels();
        driver.findElement(createButton);
        driverHelper.waitForPageReady(driver,viewLoadTimeOut);
        if(getDisplayedHotels()==initialNo+1)
        {
            loadHotels();
            return true;//return success
        }else{
            System.out.println("Hotel was not added successfully");
            return false;
        }
    }

    public void enterHotelName(String hotelName){
        System.out.println("Clicking on create button");
        driver.findElement(hotelNameInput).sendKeys(hotelName);
    }

    public void enterHotelAddress(String address){
        System.out.println("Entering hotel address " + address);
        driver.findElement(hotelAddressInput).sendKeys(address);
    }

    public void enterHotelOwner(String owner){
        System.out.println("Entering owner " + owner);
        driver.findElement(hotelOwnerInput).sendKeys(owner);
    }

    public void enterHotelPhoneInput(String phoneNumber){
        System.out.println("Entering phone number " + phoneNumber);
        driver.findElement(hotelPhoneInput).sendKeys(phoneNumber);
    }

    public void enterHotelEmail(String email){
        System.out.println("Entering email " + email);
        driver.findElement(hotelEmailInput).sendKeys(email);
    }


    public int getDisplayedHotels(){
        return driver.findElements(hotelRowIdentifier).size();
    }

    private void loadHotels(){
        List<WebElement> hotelsElements = driver.findElements(hotelRowIdentifier);
        ArrayList<HotelRow> refreshedHotels = null;
        for(int i=0; i< hotelsElements.size();i++){
            refreshedHotels.add(new HotelRow(hotelsElements.get(i), i));
        }
        hotels = refreshedHotels;
    }

    public class HotelRow {
        private WebElement hotelRow ;
        private int rowIndex ;
        private WebElement deleteButton;

        public HotelRow(WebElement row,int index){
            hotelRow = row;
            deleteButton = driver.findElements(By.cssSelector(".hotelDelete")).get(index);
        }

        public String getHotelName(){
           return getValue(hotelRow.findElements(By.cssSelector("p")), getColumn("Hotel name"));
        }

        public String getAddress(){
            return getValue(hotelRow.findElements(By.cssSelector("p")), getColumn("Address"));
        }

        public String getOwner(){
            return getValue(hotelRow.findElements(By.cssSelector("p")), getColumn("Owner"));
        }

        public String getPhoneNumber(){
            return getValue(hotelRow.findElements(By.cssSelector("p")), getColumn("Phone number"));
        }

        public String getEmail(){
            return getValue(hotelRow.findElements(By.cssSelector("p")), getColumn("Email"));
        }

        public void delete(){
            deleteButton.click();
            driverHelper.waitForPageReady(driver,viewLoadTimeOut);
        }

        private String getValue(List<WebElement> list,int index){
            if(list.size() > 0){
                return list.get(index).getText();
            }
            return "";
        }
        private int getColumn(String columnHeading){
            List<WebElement> rowHeaders = driver.findElements(By.cssSelector(".rowHeader"));

            for(int i=0; i<rowHeaders.size();i++){
                if(rowHeaders.get(i).getText().contains(columnHeading)){
                    return i;
                }
            }

            return -1;
        }

    }

}
