package com.janitri.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Updated locators to match your HTML elements
    private By userIdInput = By.id("formEmail");
    private By passwordInput = By.id("formPassword");
    private By loginButton = By.xpath("//button[contains(@class,'login-button')]");
    private By passwordToggle = By.className("passowrd-visible"); // typo in class name
    private By errorMsg = By.xpath("//div[contains(@class,'error')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLoginButtonEnabled() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginButton)).isEnabled();
    }

    public void enterUserId(String userId) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userIdInput)).clear();
        driver.findElement(userIdInput).sendKeys(userId);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isPasswordMasked() {
        String type = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).getAttribute("type");
        return type.equals("password");
    }

    public void togglePasswordVisibility() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordToggle)).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
    }

    public boolean isUserIdFieldPresent() {
        try {
            return driver.findElement(userIdInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordFieldPresent() {
        try {
            return driver.findElement(passwordInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEyeIconPresent() {
        try {
            return driver.findElement(passwordToggle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}