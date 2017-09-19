package pageObjects;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Retrier;
import util.WebConnector;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public abstract class Page extends WebConnector {

    void click(WebElement element, int timeout) {
        Retrier.retry("click " + element, () -> singleClick(element, timeout), 5, 250l);
    }

    private void singleClick(WebElement element, int timeout) {
        getLogger().info("Waiting for " + element + " to be displayed");
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        getLogger().info(element + " found after waiting for it to be displayed");
        getLogger().info("Waiting for element to be clickable - " + element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        getLogger().info("Clicking " + element);
        element.click();
        getLogger().info("Clicked " + element);
    }

    private WebDriverWait buidWebDriverWait(int timeout) {
        long sleepInMillis = 250L;
        return new WebDriverWait(getDriver(), timeout, sleepInMillis);
    }

    private void waitForElementToBeDisplayed(WebElement element, int timeout) {
        Retrier.retry("waitForElementToBeDisplayed " + element,
                () -> singleWaitForElementToBeDisplayed(element, timeout),
                4, 250l);
    }

    private void singleWaitForElementToBeDisplayed(WebElement element, int timeout) {
        getLogger().info("Waiting for " + element + " to be displayed");
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        getLogger().info(element + " found after waiting for it to be displayed");
    }

    void sendKeys(WebElement element, String text, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Sending text - " + text + " to element - " + element);
        element.sendKeys(text);
        getLogger().info("Sent text - " + text + " to element - " + element);
    }

    String getText(WebElement element, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Getting text from element - " + element);
        getLogger().info("Got text - " + element.getText() + " from element - " + element);
        return element.getText();
    }

    String getDate(Map<String, String> bookingDetails, String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        switch (bookingDetails.get(date)) {
            case "Today":
                cal.add(Calendar.DATE, 0);
                date = dateFormat.format(cal.getTime());
                break;
            case "Tomorrow":
                cal.add(Calendar.DATE, 1);
                date = dateFormat.format(cal.getTime());
                break;
            case "Yesterday":
                cal.add(Calendar.DATE, -1);
                date = dateFormat.format(cal.getTime());
                break;
            default:
                date = bookingDetails.get(date);
        }
        return date;
    }

    boolean isDisplayed(WebElement element, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        return element.isDisplayed();
    }

    void javascriptClick(WebElement element, int timeout) {
        Retrier.retry("javascriptClick " + element, () -> singleJavascriptClick(element, timeout), 4);
    }

    private void singleJavascriptClick(WebElement element, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Clicking element - " + element + " using javascript click");
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
        getLogger().info("Clicked element - " + element + " using javascript click");
    }

    private void waitForPageTitle(String title, int timeout){
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.until(ExpectedConditions.titleContains(title));
    }

    void waitForPageToLoad(String pageTitle, int timeout){
        waitForPageTitle(pageTitle, timeout);
    }

    void selectOptionFromDropDown(WebElement element, String option, int timeout){
        waitForElementToBeDisplayed(element, timeout);
        Select dropdown= new Select(element);
        dropdown.selectByVisibleText(option);
    }

    protected void sendKeyBoardKeys(WebElement element, Keys key, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Sending text - " + key + " to element - " + element);
        element.sendKeys(key);
        getLogger().info("Sent text - " + key + " to element - " + element);
    }
}