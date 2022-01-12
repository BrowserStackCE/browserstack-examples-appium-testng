package com.browserstack.test.suites;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.URL;

public class SampleTest {

    public static final String BROWSERSTACK_USERNAME = "<your_browserstack_username>";
    public static final String BROWSERSTACK_ACCESS_KEY = "<your_browserstack_access_key>";
    public static final String URL = "https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "11.0");
        capabilities.setCapability("app", "<path-to-apk>");
        capabilities.setCapability("automationName", "UiAutomator2");
        AppiumDriver driver = new AppiumDriver(new URL(URL), capabilities);

        driver.findElement(MobileBy.AccessibilityId("menu")).click();
        driver.findElement(MobileBy.xpath("//*[@text = 'Favourites']")).click();
        Assert.assertTrue(driver.findElement(MobileBy.AccessibilityId("login-form")).isDisplayed());

        driver.quit();
    }
}
