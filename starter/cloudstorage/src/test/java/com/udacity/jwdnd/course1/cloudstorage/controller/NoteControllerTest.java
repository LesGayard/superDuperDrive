package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NoteControllerTest {
    @FindBy(css = "#nav-notes-tab")
    private WebElement displayNoteModalLabelField;

    @FindBy(css = "#displayNoteModal")
    private WebElement editButton;

    @FindBy(css = "#noteModel-title")
    private WebElement noteModelTitleField;

    @FindBy(css = "#noteModel-description")
    private WebElement noteModelDescriptionField;

    @FindBy(css = "#noteModelSubmit")
    private WebElement noteModelSubmit;

    public NoteControllerTest(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void displayNote(){
        this.displayNoteModalLabelField.click();
    }
    public void createNote(String title, String description) throws InterruptedException {
        this.editButton.click();
        Thread.sleep(3000);
        this.noteModelTitleField.sendKeys(title);
        this.noteModelDescriptionField.sendKeys(description);
        this.noteModelSubmit.click();
    }


}
