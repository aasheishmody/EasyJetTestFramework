package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SeatSelectionPage extends Page {
    @FindBy(xpath = "//button[contains(text(),'Skip')]")
    private WebElement SkipButton;

    public void skipSeatSelection() {
        click(SkipButton, getShortTimeout());
    }
}