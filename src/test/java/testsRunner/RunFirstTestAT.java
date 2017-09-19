package testsRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import util.WebConnector;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber-html-report/1.json"},
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions"},
        tags = {"@booking","~@ignore"},
        monochrome = true
)

public class RunFirstTestAT extends WebConnector {
    @AfterClass
    public static void closeBrowser() {
        getDriver().quit();
    }
}