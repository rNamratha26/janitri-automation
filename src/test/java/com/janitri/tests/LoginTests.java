package com.janitri.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTests extends BaseTest {

    @Test(priority=1)
    public void testLoginButtonDisabledWhenFieldsAreEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isUserIdFieldPresent(), "User ID field should be present");
        Assert.assertTrue(loginPage.isPasswordFieldPresent(), "Password field should be present");
        Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Login button should be disabled when fields are empty");
    }

    @Test(priority=2)
    public void testPasswordMaskedButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserId("dummy");
        loginPage.enterPassword("dummyPassword");
        Assert.assertTrue(loginPage.isPasswordMasked(), "Password should be masked initially");
        loginPage.togglePasswordVisibility();
        Assert.assertFalse(loginPage.isPasswordMasked(), "Password should be unmasked after toggle");
    }

    @Test(priority=3)
    public void testInvalidLoginShowErrorMsg() {
        LoginPage loginPage = new LoginPage(driver);

        // Use explicit wait to wait for username field before interacting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

        loginPage.enterUserId("invalid");
        loginPage.enterPassword("invalid");
        loginPage.clickLogin();

        // Wait explicitly for the error message to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage"))); // Replace with actual locator if different

        String errorMsg = loginPage.getErrorMessage();
        System.out.println("Error Message: " + errorMsg);
        Assert.assertTrue(errorMsg.contains("Invalid"), "Error message should contain 'Invalid'");
    }
}