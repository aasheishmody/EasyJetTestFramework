package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HoldLuggageAndSportsEquipmentPage extends Page {
    @FindBy(xpath = "//button[contains(text(),'Skip')]")
    private WebElement SkipButton;
    @FindBy(css = "input[value='Accept & Continue >']")
    private WebElement AcceptAndContinueButton;

    private final String pageTitle = "Hold luggage & sports equipment";

    public void skipAdditionalLuggageOption() {
        waitForPageToLoad(pageTitle, getMediumTimeout());
        click(SkipButton, getShortTimeout());
    }

    public void acceptAndContinueWithoutAddingExtraLuggage() {
        click(AcceptAndContinueButton, getShortTimeout());
    }
}