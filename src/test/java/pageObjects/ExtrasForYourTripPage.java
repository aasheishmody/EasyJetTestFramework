package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExtrasForYourTripPage extends Page {
    @FindBy(xpath = "//button[contains(text(),'Add later - Skip')]")
    private WebElement AddLaterSkipButton;

    final String pageTitle = "";

    public void skipExtrasForTheTrip() {
        click(AddLaterSkipButton, getShortTimeout());
    }
}