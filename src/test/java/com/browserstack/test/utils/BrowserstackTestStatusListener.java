package com.browserstack.test.utils;

import com.browserstack.test.suites.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class BrowserstackTestStatusListener extends TestListenerAdapter {
    public BrowserstackTestStatusListener() {
    }

    private void markTestStatus(String status, String reason, WebDriver driver) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"" + status + "\", \"reason\": \"" + reason + "\"}}", new Object[0]);
        } catch (Exception var5) {
            System.out.print("Error executing javascript" + var5);
        }

    }

    public void onTestSuccess(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((TestBase) currentClass).driver;
        markTestStatus("passed", "", driver);
    }

    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((TestBase) currentClass).driver;
        String message = result.getThrowable().getMessage();
        String reason = message != null && message.length() > 254 ? message.substring(0, 254) : message;
        markTestStatus("failed", reason.replaceAll("[^a-zA-Z0-9._-]", " "), driver);
    }
}

