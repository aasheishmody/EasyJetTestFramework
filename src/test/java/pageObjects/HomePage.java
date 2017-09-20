package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

public class HomePage extends Page {

    @FindBy(css = "div[data-section-key='cookie-policy-drawer']")
    private WebElement CookiePolicyDrawer;
    @FindBy(xpath = "//button[contains(text(),'Accept & Close')]")
    private WebElement AcceptCookiePolicyButton;
    @FindBy(id = "one-way")
    private WebElement OneWayFlightCheckBox;
    @FindBy(name = "origin")
    private WebElement FlightOriginTextBox;
    @FindBy(name = "destination")
    private WebElement FlightDestinationTextBox;
    @FindBy(xpath = "//a[@class='selectable']/parent::div[1]")
    private List<WebElement> AvailableCalendarDates;
    @FindBy(xpath = "//button[contains(text(),'Show flights')]")
    private WebElement ShowFlightsButton;
    @FindBy(xpath = "//span[contains(text(),'Departing')]")
    private WebElement DepartingDateInputBox;

    public void open() {
        getDriver().get(getBaseURL());
        if (isDisplayed(CookiePolicyDrawer, getShortTimeout()))
            click(AcceptCookiePolicyButton, getShortTimeout());
    }

    public void selectOneWayOptionForBookingFlight() {
        javascriptClick(OneWayFlightCheckBox, getMediumTimeout());
    }

    private void enterOriginAirport(String origin) {
        FlightOriginTextBox.clear();
        sendKeys(FlightOriginTextBox, origin, getShortTimeout());
    }

    private void enterDestinationAirport(String destination) {
        FlightDestinationTextBox.clear();
        sendKeys(FlightDestinationTextBox, destination, getShortTimeout());
    }

    private void selectDepartingDate(String departingDate) {
        javascriptClick(DepartingDateInputBox, getMediumTimeout());
        for (WebElement DepartingDateOnTheCalendar : AvailableCalendarDates)
            try {
                if (DepartingDateOnTheCalendar.getAttribute("data-date").equals(departingDate))
                    click(DepartingDateOnTheCalendar, getMediumTimeout());
            } catch (Exception e) {

            }
    }

    public void clickShowFlightsButton() {
        click(ShowFlightsButton, getMediumTimeout());
    }

    public void enterflightDetails(Map<String, String> flightDetails) {
        enterOriginAirport(flightDetails.get("Origin"));
        enterDestinationAirport(flightDetails.get("Destination"));
        selectDepartingDate(flightDetails.get("Departing Date"));
    }
}