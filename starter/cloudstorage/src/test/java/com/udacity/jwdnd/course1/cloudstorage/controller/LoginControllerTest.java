package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginControllerTest {
    /* DEFINING ELEMENTS SELECTORS */
    @FindBy(css = "#inputUsername")
    private WebElement usernameField;

    @FindBy(css = "#inputPassword")
    private WebElement passwordField;

    @FindBy(css = "#inputButton")
    private WebElement buttonField;


    /* INITIALIZING ELEMENTS IN THE CONSTRUCTOR */
    public LoginControllerTest(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    /* CREATING HELPER METHOD */
    public void login(String username, String password){
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.buttonField.click();
    }

}
