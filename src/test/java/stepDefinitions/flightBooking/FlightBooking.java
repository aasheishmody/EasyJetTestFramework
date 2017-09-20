package stepDefinitions.flightBooking;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import pageObjects.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static util.LoggerHelper.*;

public class FlightBooking extends Page {

    private final HomePage homePage;
    private final ChooseYourFlightsPage chooseYourFlightsPage;
    private final SeatSelectionPage seatSelectionPage;
    private final HoldLuggageAndSportsEquipmentPage holdLuggageAndSportsEquipmentPage;
    private final ExtrasForYourTripPage extrasForYourTripPage;
    private final HotelsPage hotelsPage;
    private final CarHirePage carHirePage;
    private final CheckOutPage checkOutPage;

    public FlightBooking() {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        chooseYourFlightsPage = PageFactory.initElements(getDriver(), ChooseYourFlightsPage.class);
        seatSelectionPage = PageFactory.initElements(getDriver(), SeatSelectionPage.class);
        holdLuggageAndSportsEquipmentPage = PageFactory.initElements(getDriver(), HoldLuggageAndSportsEquipmentPage.class);
        extrasForYourTripPage = PageFactory.initElements(getDriver(), ExtrasForYourTripPage.class);
        hotelsPage = PageFactory.initElements(getDriver(), HotelsPage.class);
        carHirePage = PageFactory.initElements(getDriver(), CarHirePage.class);
        checkOutPage = PageFactory.initElements(getDriver(), CheckOutPage.class);
    }

    @Given("^I am on the 'Home' page$")
    public void iAmOnTheHomePage() throws Throwable {
        navigating("to the HomePage page", homePage::open);
    }

    @And("^I select a one way flight with the following details$")
    public void iSelectAOneWayFlightWithTheFollowingDetails(DataTable table) throws Throwable {
        selecting("one way flight", () -> {
            homePage.selectOneWayOptionForBookingFlight();
            Map<String, String> flightDetails = getTableDetails(table);
            homePage.enterflightDetails(flightDetails);
            homePage.clickShowFlightsButton();
            chooseYourFlightsPage.selectFirstAvailableFlightForTheChosenDate();
            chooseYourFlightsPage.clickContinueButton();
        });
    }

    @And("^I skip all the optional extras$")
    public void iSkipAllTheOptionalExtras() throws Throwable {
        skipping("all the optional extras", () -> {
            seatSelectionPage.skipSeatSelection();
            holdLuggageAndSportsEquipmentPage.skipAdditionalLuggageOption();
            holdLuggageAndSportsEquipmentPage.acceptAndContinueWithoutAddingExtraLuggage();
            extrasForYourTripPage.skipExtrasForTheTrip();
            hotelsPage.skipHotelBooking();
            carHirePage.skipCarHire();
        });
    }

    @And("^I login with the following valid details on the 'Checkout' page$")
    public void iLoginWithTheFollowingValidDetailsOnTheCheckoutPage(DataTable table) throws Throwable {
        logging("with valid details", () -> {
            Map<String, String> signinDetails = getTableDetails(table);
            try {
                checkOutPage.signinWithExistingDetails(signinDetails);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @And("^I enter the Passenger details on the 'Checkout' page$")
    public void iEnterThePassengerDetailsOnTheCheckoutPage(DataTable table) throws Throwable {
        entering("the Passenger details on the 'Checkout' page", () -> {
            Map<String, String> passengerDetails = getTableDetails(table);
            try {
                checkOutPage.enterPassengerDetails(passengerDetails);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Then("^the booking details are displayed correctly on the 'Checkout' page$")
    public void theBookingDetailsAreDisplayedCorrectlyOnTheCheckoutPage(DataTable table) throws Throwable {
        asserting("that the booking details are displayed correctly on the 'Checkout' page", () -> {
            Map<String, String> bookingDetails = getTableDetails(table);
            try {
                Assert.assertTrue(checkOutPage.bookingDetailsAreDisplayedCorrectly(bookingDetails));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private Map<String, String> getTableDetails(DataTable table) {
        List<Map<String, String>> tableDetailsRow = table.asMaps(String.class, String.class);
        Map<String, String> tableDetails = null;
        for (Map<String, String> tableDetail : tableDetailsRow) {
            tableDetails = tableDetail;
        }
        return tableDetails;
    }
}