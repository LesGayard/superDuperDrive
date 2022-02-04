package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NoteControllerTest {

    /* WEB-ELEMENTS */
    @FindBy(css = "#nav-notes-tab")
    private WebElement displayNoteModalLabelField;

    @FindBy(css = "#displayNoteModal")
    private WebElement AddANewNoteButton;

    @FindBy(css = "#noteModel-title")
    private WebElement noteModelTitleField;

    @FindBy(css = "#noteModel-description")
    private WebElement noteModelDescriptionField;

    @FindBy(css = "#noteModelSubmit")
    private WebElement noteModelSubmit;

    @FindBy(css = "#editNote")
    private WebElement editNoteButton;

    @FindBy(css = "#deleteNote")
    private WebElement deleteNoteButton;


    /* CONSTRUCTOR */
    public NoteControllerTest(WebDriver driver){
        PageFactory.initElements(driver, this);
    }


    /* HELPER METHODS */
    public void displayNote(){
        this.displayNoteModalLabelField.click();
    }

    public void createNote(String title, String description) throws InterruptedException {
        this.AddANewNoteButton.click();
        Thread.sleep(3000);
        this.noteModelTitleField.sendKeys(title);
        this.noteModelDescriptionField.sendKeys(description);
        this.noteModelSubmit.click();
    }

    public void updateNote(String title, String description)throws InterruptedException{
        Thread.sleep(3000);
        this.editNoteButton.click();
        Thread.sleep(3000);
        this.noteModelTitleField.sendKeys(title);
        this.noteModelDescriptionField.sendKeys(description);
        this.noteModelSubmit.click();
    }

    public void deleteNote(){
        this.deleteNoteButton.click();
    }


}
