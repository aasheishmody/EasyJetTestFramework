package util;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.picocontainer.classname.ClassName;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebConnector {

    private static int shortTimeout;
    private static int mediumTimeout;
    private static int longTimeout;
    private static Logger logger;
    private static WebDriver driver;
    private static Properties properties;
    private static String baseURL;
    private static int height;
    private static int width;
    private static String browser;

    protected static int getShortTimeout() {
        return shortTimeout;
    }

    private static void setShortTimeout(int shortTimeout) {
        WebConnector.shortTimeout = shortTimeout;
    }

    protected static int getMediumTimeout() {
        return mediumTimeout;
    }

    private static void setMediumTimeout(int mediumTimeout) {
        WebConnector.mediumTimeout = mediumTimeout;
    }

    private static int getLongTimeout() {
        return longTimeout;
    }

    private static void setLongTimeout(int longTimeout) {
        WebConnector.longTimeout = longTimeout;
    }

    protected static Logger getLogger() {
        return logger;
    }

    private static void setLogger(Logger logger) {
        WebConnector.logger = logger;
    }

    protected static WebDriver getDriver() {
        return driver;
    }

    private static void setDriver(WebDriver driver) {
        WebConnector.driver = driver;
    }

    private static Properties getProperties() {
        return properties;
    }

    private static void setProperties(Properties properties) {
        WebConnector.properties = properties;
    }

    protected static String getBaseURL() {
        return baseURL;
    }

    private static void setBaseURL(String baseURL) {
        WebConnector.baseURL = baseURL;
    }

    private static int getHeight() {
        return height;
    }

    private static void setHeight(int height) {
        WebConnector.height = height;
    }

    private static int getWidth() {
        return width;
    }

    private static void setWidth(int width) {
        WebConnector.width = width;
    }

    private static String getBrowser() {
        return browser;
    }

    private static void setBrowser(String browser) {
        WebConnector.browser = browser;
    }

    protected void initialize() {
        initializeLogger();
        initializeProperties();
        setProperties();
        setBrowser();
        manageBrowser();
    }

    private void initializeLogger() {
        setLogger(Logger.getLogger(ClassName.class.getName()));
    }

    private void initializeProperties() {
        getLogger().info("INITIALIZING PROPERTIES");
        setProperties(new Properties());
        try {
            getProperties().load(WebConnector.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLogger().info("PROPERTIES INITIALIZED");
    }

    private void setProperties() {
        getLogger().info("SETTING PROPERTIES");
        String width;
        String height;
        String shortTimeout;
        String mediumTimeout;
        String longTimeout;
        setBaseURL(getProperties().getProperty("environment"));
        setBrowser(getProperties().getProperty("browser"));
        width = getProperties().getProperty("width");
        height = getProperties().getProperty("height");
        setWidth(Integer.parseInt(String.valueOf(width)));
        setHeight(Integer.parseInt(String.valueOf(height)));
        shortTimeout = getProperties().getProperty("shortTimeout");
        mediumTimeout = getProperties().getProperty("mediumTimeout");
        longTimeout = getProperties().getProperty("longTimeout");
        setShortTimeout(Integer.parseInt(String.valueOf(shortTimeout)));
        setMediumTimeout(Integer.parseInt(String.valueOf(mediumTimeout)));
        setLongTimeout(Integer.parseInt(String.valueOf(longTimeout)));
        getLogger().info("PROPERTIES SET");
    }

    private void setBrowser() {
        getLogger().info("SETTING BROWSER");
        switch (getBrowser()) {
            case "chrome":
                getLogger().info("Opening Chrome");
                if (getDriver() == null){
                    ChromeDriverManager.getInstance().setup();
                    setDriver(new ChromeDriver());
                    getLogger().info("Chrome opened");
                }
                break;
            case "firefox":
                if (getDriver() == null) {
                    getLogger().info("Opening Firefox");
                    FirefoxDriverManager.getInstance().setup();
                    setDriver(new FirefoxDriver());
                    getLogger().info("Firefox opened");
                }
                break;
        }
        getLogger().info("BROWSER SET");
    }

    private void manageBrowser() {
        getLogger().info("MANAGING BROWSER");
        getDriver().manage().timeouts().pageLoadTimeout(getLongTimeout(), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(getMediumTimeout(), TimeUnit.SECONDS);
        getDriver().manage().window().setSize(new Dimension(getWidth(), getHeight()));
        getLogger().info("BROWSER MANAGED");
    }
}