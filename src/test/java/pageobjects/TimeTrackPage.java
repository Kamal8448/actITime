package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ObjectRepoHandler;
import utils.PropertyFileReader;

import java.io.FileNotFoundException;
import java.time.Duration;

public class TimeTrackPage extends BasePage {
    String s;
    public TimeTrackPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTimeTrackPageTitle() {
        try {
            String expectedTitle = PropertyFileReader.getPropertyValue("objectrepository.properties", "expectedTitle");
            return super.getTitle(expectedTitle);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserProfile() {

            String actualProfile ="USER_PROFILE_NAME";
            WebElement userProfile=super.getAnyElement(actualProfile);
            System.out.println("User prfile in time track:  "+userProfile);
            s=userProfile.toString();
           return  s;

    }
}
