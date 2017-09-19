package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChooseYourFlightsPage extends Page {
    @FindBy(xpath = "//span[contains(text(), 'Monday 12th March')]/parent::span/parent::span")
    private WebElement FirstAvailableFlight;
    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private WebElement ContinueButton;

    public void selectFirstAvailableFlightForTheChosenDate() {
        click(FirstAvailableFlight, getShortTimeout());
    }

    public void clickContinueButton() {
        click(ContinueButton, getShortTimeout());
    }


}