package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        Thread.sleep(10000L);
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
        if(passengerDetails.get("Reason for travel").contains("Business"))
            click(BusinessRadioButton, getShortTimeout());
        else
            click(PleasureRadioButton, getShortTimeout());
    }
}