package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class CheckOutPage extends Page {
    @FindBy(id = "signin-username")
    private WebElement UsernameInputBox;
    @FindBy(id = "signin-password")
    private WebElement PasswordInputBox;
    @FindBy(id = "signin-login")
    private WebElement SigninButton;
    @FindBy(css = "input[id*='reason-1']")
    private WebElement BusinessRadioButton;
    @FindBy(css = "input[id*='reason-2']")
    private WebElement PleasureRadioButton;
    @FindBy(id = "title-dropdown-adult-1")
    private WebElement TitleDropdownForPassengerDetails;
    @FindBy(id = "firstname-textbox-adult-1")
    private WebElement FirstNameInputBoxForPassengerDetails;
    @FindBy(id = "lastname-textbox-adult-1")
    private WebElement LastNameInputBoxForPassengerDetails;
    @FindBy(id = "age-dropdown-adult-1")
    private WebElement AgeDropdownForPassengerDetails;
    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private List<WebElement> ContinueButton;
    @FindBy(xpath = "//li[contains(@class, 'email')]/span[contains(@class, 'booker-detail-text')]")
    private WebElement BookerEmail;
    @FindBy(xpath = "//li[@class='passenger']/div[@class='name']")
    private WebElement PassengerDetails;
    @FindBy(xpath = "//span[@class='origin-name']")
    private WebElement ConfirmedOrigin;
    @FindBy(xpath = "//span[@class='destination-name']")
    private WebElement ConfirmedDestination;
    @FindBy(xpath = "//div[contains(@class, 'departure')]//span[contains(@class, 'date')] ")
    private WebElement ConfirmedDepartingDate;

    final String pageTitle = "Checkout";

    public void signinWithExistingDetails(Map<String, String> signinDetails) throws InterruptedException {
        enterUsername(signinDetails);
        enterPassword(signinDetails);
        clickSigninButton();
    }

    private void clickSigninButton() {
        click(SigninButton, getShortTimeout());
    }

    private void enterUsername(Map<String, String> signinDetails) {
        sendKeys(UsernameInputBox, signinDetails.get("Username"), getMediumTimeout());
    }

    private void enterPassword(Map<String, String> signinDetails) {
        sendKeys(PasswordInputBox, signinDetails.get("Password"), getMediumTimeout());
    }

    public void enterPassengerDetails(Map<String, String> passengerDetails) throws InterruptedException {
        selectReasonForTravelForPassengerDetails(passengerDetails);
        selectTitleForPassengerDetails(passengerDetails);
        enterFirstnameForPassengerDetails(passengerDetails);
        enterLastnameForPassengerDetails(passengerDetails);
        selectAgeForPassengerDetails(passengerDetails);
        clickContinueButton();
    }

    private void clickContinueButton() throws InterruptedException {
        click(ContinueButton.get(1), getMediumTimeout());
    }

    private void selectAgeForPassengerDetails(Map<String, String> passengerDetails) {
        selectOptionFromDropDown(AgeDropdownForPassengerDetails, passengerDetails.get("Age"), getMediumTimeout());
    }

    private void enterLastnameForPassengerDetails(Map<String, String> passengerDetails) {
        sendKeys(LastNameInputBoxForPassengerDetails, passengerDetails.get("Last Name"), getMediumTimeout());
    }

    private void selectTitleForPassengerDetails(Map<String, String> passengerDetails) {
        selectOptionFromDropDown(TitleDropdownForPassengerDetails, passengerDetails.get("Title"), getMediumTimeout());
    }

    private void enterFirstnameForPassengerDetails(Map<String, String> passengerDetails) {
        sendKeys(FirstNameInputBoxForPassengerDetails, passengerDetails.get("First Name"), getMediumTimeout());
    }

    private void selectReasonForTravelForPassengerDetails(Map<String, String> passengerDetails) {
        if (passengerDetails.get("Reason for travel").contains("Business"))
            click(BusinessRadioButton, getShortTimeout());
        else
            click(PleasureRadioButton, getShortTimeout());
    }

    public boolean bookingDetailsAreDisplayedCorrectly(Map<String, String> bookingDetails) throws ParseException {
        String expectedBookerEmailAddress = bookingDetails.get("Booker Email Address");
        String expectedPassengerFirstName = bookingDetails.get("Passenger First Name");
        String expectedPassengerLastName = bookingDetails.get("Passenger Last Name");
        String expectedPassengerAge = bookingDetails.get("Passenger Age");
        String expectedOrigin = bookingDetails.get("Origin");
        String expectedDestination = bookingDetails.get("Destination");
        String expectedDepartingdate = bookingDetails.get("Departing Date");
        Date date = getParsedDate(expectedDepartingdate, "yyyy-MM-dd");
        expectedDepartingdate = getFormattedDate(date);
        String actualBookerEmailAddress = getText(BookerEmail, getShortTimeout());
        String actualPassengerDetails = getText(PassengerDetails, getShortTimeout());
        String actualOrigin = getText(ConfirmedOrigin, getShortTimeout());
        String actualDestination = getText(ConfirmedDestination, getShortTimeout());
        String actualDepartingDate = getText(ConfirmedDepartingDate, getShortTimeout());
        actualDepartingDate = actualDepartingDate.replaceAll("(?<=\\d)(rd|st|nd|th)\\b", "");

        return (expectedBookerEmailAddress.equalsIgnoreCase(actualBookerEmailAddress)) &&
                (actualPassengerDetails.contains(expectedPassengerFirstName)) &&
                (actualPassengerDetails.contains(expectedPassengerLastName)) &&
                (actualPassengerDetails.contains(expectedPassengerAge)) &&
                (expectedOrigin.contains(actualOrigin)) &&
                (expectedDestination.contains(actualDestination)) &&
                (expectedDepartingdate.equals(actualDepartingDate));
    }
}