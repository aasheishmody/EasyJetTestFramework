package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelsPage extends Page {
    @FindBy(xpath = "//button[contains(text(),'Skip')]")
    private WebElement SkipButton;

    final String pageTitle = "";

    public void skipHotelBooking() {
        click(SkipButton, getShortTimeout());
    }
}