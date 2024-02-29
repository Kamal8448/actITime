package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PropertyFileReader;

import java.io.FileNotFoundException;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
//    @FindBy(name="username")
//    WebElement usernameElement1;

    public LoginPage enterUserName(String userName){
        WebElement usernameElement=super.getAnyElement("USER_NAME_TEXT_BOX");
        usernameElement.sendKeys(userName);
//        this.usernameElement1.sendKeys("name_username");
        return this; // same page is returning thats why we are using this
    }
    public LoginPage enterPassword(String password){
        WebElement passwordElement=super.getAnyElement("PASSWORD_TEXT_BOX");
        passwordElement.sendKeys(password);
        return this;
    }
    public TimeTrackPage submitButton(){
        WebElement submitButton=super.getAnyElementByTime("LOGIN_BUTTON");
        submitButton.click();
       // return PageFactory.initElements(this.driver,TimeTrackPage.class);
               return new TimeTrackPage(driver);
    }
}
