package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarHirePage extends Page {
    @FindBy(xpath = "//button[contains(text(),'Skip')]")
    private WebElement SkipButton;

    final String pageTitle = "Car hire";

    public void skipCarHire() {
        waitForPageToLoad(pageTitle, getMediumTimeout());
        click(SkipButton, getShortTimeout());
    }
}