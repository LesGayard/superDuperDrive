package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeControllerTest {
    @FindBy(css = "logout")
    private WebElement logoutButtonField;


    public HomeControllerTest(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    /* HELPER METHODS */
    public void homePage(){
        System.out.println("home page displayed !!");
    }
}
