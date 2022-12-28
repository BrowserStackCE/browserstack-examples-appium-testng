package com.browserstack.test.suites;


import com.browserstack.test.utils.MobileHelper;
import com.browserstack.test.utils.ScreenshotListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.MutableCapabilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Listeners({ScreenshotListener.class})
public class TestBase {

    public AppiumDriver driver;

    public MobileHelper mobileHelper;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {

        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
        bstackOptions.putIfAbsent("source", "testng-java:sample-sdk:v1.0");
        capabilities.setCapability("bstack:options", bstackOptions);
        driver = new AppiumDriver(new URL("https://hub.browserstack.com/wd/hub"), capabilities);

        mobileHelper = new MobileHelper(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
