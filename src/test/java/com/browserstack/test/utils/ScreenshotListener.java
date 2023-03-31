package com.browserstack.test.utils;

import com.browserstack.test.suites.TestBase;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter {
    public ScreenshotListener() {
    }

    @Attachment(
            value = "Failure screenshot",
            type = "image/png"
    )
    public byte[] attachFailedScreenshot(WebDriver driver) {
        return (byte[])((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    public void onTestFailure(ITestResult result) {
        if (!result.isSuccess()) {
            Object currentClass = result.getInstance();
            WebDriver driver = ((TestBase)currentClass).driver;
            attachFailedScreenshot(driver);
        }

    }
}
