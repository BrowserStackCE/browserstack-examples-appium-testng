package com.browserstack.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;

public class OrdersPage extends BasePage {
    @AndroidFindBy(accessibility = "order-item")
    @iOSXCUITFindBy(accessibility = "order-item")
    private List<MobileElement> orderItems;

    public OrdersPage(AppiumDriver<?> driver) {
        super(driver);
    }

    public int getItemsFromOrder() {
        return orderItems.size();
    }
}
