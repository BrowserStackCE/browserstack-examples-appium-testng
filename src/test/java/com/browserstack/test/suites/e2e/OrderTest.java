package com.browserstack.test.suites.e2e;

import com.browserstack.app.pages.HomePage;
import com.browserstack.app.pages.OrdersPage;
import com.browserstack.test.suites.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTest extends TestBase {

    @Test
    public void placeOrder() {
        HomePage page = new HomePage(driver)
                .navigateToSignIn()
                .loginWith("fav_user", "testingisfun99")
                .addProductToCart("12")
                .addProductToCart("16")
                .addProductToCart("23")
                .openCart()
                .proceedToCheckout()
                .enterShippingDetails("firstname", "lastname", "address", "state", "12345")
                .continueShopping();

        OrdersPage ordersPage = page.navigateToOrders();
        Assert.assertEquals(ordersPage.getItemsFromOrder(), 3);
    }
}
