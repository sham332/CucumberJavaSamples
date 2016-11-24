package skeleton;

import core.DriverFactory;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import org.openqa.selenium.remote.RemoteWebDriver;
import views.Home;
import views.HomeAuthenticated;

import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class Stepdefs {

    private static HomeAuthenticated homeAuthenticatedView;
    private static Home homeview;
    private static HotelDetails hotelAdded;
    private static List<HotelDetails> multipleHotels = new ArrayList<HotelDetails>();
    private static RemoteWebDriver driver;
    @Before
    public void openBrowser() throws IOException {
        driver = DriverFactory.getDriver();
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.get("http://192.168.99.100:3003/");

    }
    @Given("^I have (\\d+) cukes in my belly$")
    public void I_have_cukes_in_my_belly(int cukes) throws Throwable {
        Belly belly = new Belly();
        belly.eat(cukes);
    }

    @Given("^a user is logged in:$")
    public void userIsLoggedIn(List<LoginCredentials> credentials) {
        homeview = new Home();
        homeview.openLogin();
        homeview.loginModal.enterUserName(credentials.get(0).username);
        homeview.loginModal.enterPassword(credentials.get(0).password);
        homeview.loginModal.clickLogin();
        homeAuthenticatedView = new HomeAuthenticated();
    }

    @After
    public void closeBrowser()
    {
        DriverFactory.setDriver(null);
    }

    @When("^he enters hotel details:$")
    public void entersHotelDetails(List<HotelDetails> hotel) {
        homeAuthenticatedView.enterHotelName(hotel.get(0).hotelName);
        homeAuthenticatedView.enterHotelAddress(hotel.get(0).address);
        homeAuthenticatedView.enterHotelOwner(hotel.get(0).owner);
        homeAuthenticatedView.enterHotelPhoneInput(hotel.get(0).phoneNumber);
        homeAuthenticatedView.enterHotelEmail(hotel.get(0).email);
        hotelAdded = hotel.get(0);
    }
    @When("^clicks create$")
    public void clickCreateHotel() throws Throwable {
        assertTrue("Checking hotel created", homeAuthenticatedView.clickOnCreateHotel());
    }

    @Then("^hotel  details should be saved and displayed$")
    public void hotelDetailsSavedAndDisplayed()  {

            assertTrue("Checking hotel created is displayed on list", homeAuthenticatedView.checkHotelAdded(hotelAdded.hotelName, hotelAdded.address, hotelAdded.owner, hotelAdded.phoneNumber, hotelAdded.email));
    }

    @Then("^multiple hotel  details should be saved and displayed$")
    public void multiplehotelDetailsSavedAndDisplayed()  {
        for(HotelDetails hotel:multipleHotels) {
            assertTrue("Checking hotel created is displayed on list", homeAuthenticatedView.checkHotelAdded(hotel.hotelName, hotel.address, hotel.owner, hotel.phoneNumber, hotel.email));
        }
    }

    @When("^he enters hotel details and clicks create:$")
    public void he_enters_hotel_details_and_clicks_create(List<HotelDetails> hotels) throws Throwable {
        for(HotelDetails hotel: hotels){
            homeAuthenticatedView.enterHotelName(hotel.hotelName);
            homeAuthenticatedView.enterHotelAddress(hotel.address);
            homeAuthenticatedView.enterHotelOwner(hotel.owner);
            homeAuthenticatedView.enterHotelPhoneInput(hotel.phoneNumber);
            homeAuthenticatedView.enterHotelEmail(hotel.email);
            clickCreateHotel();
            multipleHotels.add(hotel);
        }
    }

    @When("^clicks delete on hotel with below details$")
    public void clicks_delete_on_hotel_with_below_details(List<HotelDetails> hotel) throws Throwable {
        hotelAdded = hotel.get(0);
        homeAuthenticatedView.deleteHotel(hotelAdded.hotelName, hotelAdded.address, hotelAdded.owner, hotelAdded.phoneNumber, hotelAdded.email);
    }

    @Then("^hotel should get deleted and removed from the list$")
    public void hotel_should_get_deleted_and_removed_from_the_list() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertFalse("Checking hotel deleted from displayed list", homeAuthenticatedView.checkHotelAdded(hotelAdded.hotelName, hotelAdded.address, hotelAdded.owner, hotelAdded.phoneNumber, hotelAdded.email));
    }


    private class LoginCredentials{
        String username;
        String password;
    }

    private class HotelDetails{
        String hotelName;
        String address;
        String owner;
        String phoneNumber;
        String email;
    }
}

