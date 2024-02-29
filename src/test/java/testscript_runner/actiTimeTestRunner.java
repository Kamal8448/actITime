package testscript_runner;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.BasePage;
import pageobjects.LoginPage;
import pageobjects.TimeTrackPage;
import utils.BrowserFactory;
import utils.CommonUtil;
import utils.PropertyFileReader;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;

@Test
public class actiTimeTestRunner {
    public WebDriver driver = null;
    String pass_fail=null;

    public void loggingIn() throws FileNotFoundException {
        try {
            driver = BrowserFactory.getDriver("chrome");
            String url = PropertyFileReader.getPropertyValue("config.properties", "url");
            driver.get(url);
            //enter username and password
            LoginPage lp=new LoginPage(driver);
            String un= PropertyFileReader.getPropertyValue("config.properties","username");
            lp.enterUserName(un);
            String pw=PropertyFileReader.getPropertyValue("config.properties","password");
            lp.enterPassword(pw);
            TimeTrackPage t;
            t = lp.submitButton();

            //get expected and actual title
            String expectedTitle = PropertyFileReader.getPropertyValue("objectrepository.properties","expectedTitle");
            System.out.println("expected title:  "+expectedTitle);
            String actualTitle = t.getTimeTrackPageTitle();
            System.out.println("actual title:  "+actualTitle);
            Assert.assertEquals(expectedTitle,actualTitle);
            pass_fail="pass";

            //BrowserFactory.closeBrowser();
           // BrowserFactory.closeAllBrowser();
        } catch (Throwable e) {
            CommonUtil.takeScreenshort(driver);
            pass_fail="fail";
                throw e;
            }
               finally {
            BrowserFactory.closeBrowser();
            BrowserFactory.closeAllBrowser();
            System.out.println("Test case completed execution with the result as = " + pass_fail);

        }
    }
@Test
    public void secondway_loggingIn() throws FileNotFoundException {
        try {
            driver = BrowserFactory.getDriver("chrome");
            String url = PropertyFileReader.getPropertyValue("config.properties", "url");
            String un= PropertyFileReader.getPropertyValue("config.properties","username");
            String pw=PropertyFileReader.getPropertyValue("config.properties","password");
           // String expectedTitle = PropertyFileReader.getPropertyValue("objectrepository.properties","expectedTitle");

            //String actualTitle=BrowserFactory.openUrl(url).enterUserName(un).enterPassword(pw).submitButton().getTimeTrackPageTitle();

            // valdating user profile
            TimeTrackPage ttp = BrowserFactory.openUrl(url).enterUserName(un).enterPassword(pw).submitButton();
            String expectedProfile=PropertyFileReader.getPropertyValue("objectrepository.properties","expected_ProfileName");
            System.out.println(expectedProfile);
            String actual=ttp.getUserProfile();
            System.out.println("actual profile: " + actual);
           // Assert.assertEquals(actualProfile,expectedProfile);

//            System.out.println("actual title:  "+actualTitle);
            //Assert.assertEquals(expectedTitle,actualTitle);
            pass_fail="pass";
        } catch (Throwable e) {
            CommonUtil.takeScreenshort(driver);
            pass_fail="fail";
            throw e;
        }
        finally {
            BrowserFactory.closeBrowser();
            BrowserFactory.closeAllBrowser();
            System.out.println("Test case completed execution with the result as = " + pass_fail);

        }
    }
    public void beforeMethod() throws  FileNotFoundException{
        driver = BrowserFactory.getDriver("chrome");
        String url = PropertyFileReader.getPropertyValue("config.properties", "url");
        String un= PropertyFileReader.getPropertyValue("config.properties","username");
        String pw=PropertyFileReader.getPropertyValue("config.properties","password");
        TimeTrackPage ttp = BrowserFactory.openUrl(url).enterUserName(un).enterPassword(pw).submitButton();



    }
}
