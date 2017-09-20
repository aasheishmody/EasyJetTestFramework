package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Retrier;
import util.WebConnector;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    String getFormattedDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("EEE dd MMM");
        return dateFormat.format(date);
    }

    Date getParsedDate(String unparsedDate, String pattern) throws ParseException {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.parse(unparsedDate);
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

    private void waitForPageTitle(String title, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.until(ExpectedConditions.titleContains(title));
    }

    void waitForPageToLoad(String pageTitle, int timeout) {
        waitForPageTitle(pageTitle, timeout);
    }

    void selectOptionFromDropDown(WebElement element, String option, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(option);
    }
}