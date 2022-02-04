package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialControllerTest {
    
    @FindBy(css = "#nav-credentials-tab")
    private WebElement credentialsTab;
    
    @FindBy(css = "#addCredentialButton")
    private WebElement addCredentialButton;
    
    @FindBy(css = "#credential-url")
    private WebElement credentialsUrl;
    
    @FindBy(css ="#credential-username")
    private WebElement credentialUsername;
    
    @FindBy(css ="#credential-password")
    private WebElement credentialPassword;

    @FindBy(css = "#credentialSaveButton")
    private WebElement credentialSaveButton;

    @FindBy(css ="#credentialEdit")
    private WebElement credentialEdit;

    @FindBy(css = "#credentialDelete")
    private WebElement credentialDelete;

    public CredentialControllerTest(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    /* HELPER METHODS */
    public void displayCredential(){
        this.credentialsTab.click();

    }
    public void createCredential(String url, String username, String password) throws InterruptedException {
        this.addCredentialButton.click();
        Thread.sleep(3000);
        this.credentialsUrl.sendKeys(url);
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.sendKeys(password);
        this.credentialSaveButton.click();
    }

}
