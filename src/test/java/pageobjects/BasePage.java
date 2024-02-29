package pageobjects;

import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ObjectRepoHandler;
import utils.PropertyFileReader;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

public class BasePage {
    protected WebDriver driver;
    private String locatorType, locatorValue;
    @FindBy(xpath = "//a[normalize-space(@class)='userProfileLink username']")
    WebElement profileElement;

    @FindBy(how = How.XPATH, using = "//a[normalize-space(@class)='userProfileLink username']")
    WebElement profilenameElement;

    public WebElement getAnyElement(String properties) { // properties is key here
        try {
            System.out.println("properties value:  "+properties);
            String locator = PropertyFileReader.getPropertyValue("objectrepository.properties", properties);
            locatorType = locator.substring(0, locator.indexOf("_"));
             locatorValue = locator.substring(locator.indexOf("_") + 1);
            System.out.println("seperating in Basepage:  "+locatorType + "\t\t" +locatorValue);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ObjectRepoHandler.getElement(driver, locatorType, locatorValue);

    }

    public WebElement getAnyElementByTime(String properties) { // properties is key here
        try {
            String locator = PropertyFileReader.getPropertyValue("objectrepository.properties", properties);
            locatorType = locator.substring(0, locator.indexOf("_"));
            locatorValue = locator.substring(locator.indexOf("_") + 1);

        } catch (FileNotFoundException e) {
    throw new RuntimeException(e);
        }
        return ObjectRepoHandler.getElementByTime(driver, locatorType, locatorValue);

    }
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public  String getTitle(String title) {
        FluentWait<WebDriver> fw= new WebDriverWait(driver, Duration.ofSeconds(60));
        fw.pollingEvery(Duration.ofSeconds(5));
        fw.until(ExpectedConditions.titleIs(title));
        return this.driver.getTitle();
    }
}
