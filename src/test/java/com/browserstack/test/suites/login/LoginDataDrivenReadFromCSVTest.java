package com.browserstack.test.suites.login;

import com.browserstack.test.suites.TestBase;
import com.browserstack.test.utils.CsvUtil;
import io.appium.java_client.MobileBy;
import java.util.Iterator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDataDrivenReadFromCSVTest extends TestBase {

    @DataProvider(name = "login_error_messages")
    public Iterator<String[]> provider() throws Exception {
        return CsvUtil.readAll("src/test/resources/data/users.csv", false);
    }

    @Test(dataProvider = "login_error_messages")
    public void validateErrors(String username, String password, String error) {
        driver.findElement(MobileBy.AccessibilityId("menu")).click();
        driver.findElement(MobileBy.AccessibilityId("nav-signin")).click();

        driver.findElement(MobileBy.AccessibilityId("username-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Accepted usernames are']", username);

        driver.findElement(MobileBy.AccessibilityId("password-input")).click();
        mobileHelper.selectFromPickerWheel("//XCUIElementTypePickerWheel[@value='Password for all users']", password);

        driver.findElement(MobileBy.AccessibilityId("login-btn")).click();
        Assert.assertEquals(driver.findElement(MobileBy.AccessibilityId("api-error")).getText(), error);
    }
}
