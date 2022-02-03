package com.udacity.jwdnd.course1.cloudstorage.controller;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpControllerTest {

    /* DEFINING ELEMENTS SELECTORS */
    @FindBy(css = "#inputFirstName")
    private WebElement firstNameField;

    @FindBy(css = "#inputLastName")
    private WebElement lastNameField;

    @FindBy(css = "#inputUsername")
    private WebElement usernameField;

    @FindBy(css = "#inputPassword")
    private WebElement passwordField;

    @FindBy(css = "#inputSubmit")
    private WebElement submitButtonField;

    /* INITIALIZING ELEMENTS IN THE CONSTRUCTOR */
    public SignUpControllerTest(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    /* CREATE HELPER METHOD
    *  signs up a new user,
    *  logs that user in,
    * verifies that they can access the home page,
    * then logs out
    * and verifies that the home page is no longer accessible.
    *  */
    public void signup(String firstname, String lastname, String username, String password) {
        this.firstNameField.sendKeys(firstname);
        this.lastNameField.sendKeys(lastname);
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButtonField.click();
    }

}
