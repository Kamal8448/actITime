package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ObjectRepoHandler {

    public static WebElement getElement(WebDriver driver, String locatorType, String locatorValue) {
        System.out.println("seperating in objectrepohandler:  "+locatorType + "\t" +locatorValue);
        WebElement element = null;
        if (locatorType.equalsIgnoreCase("id")) {
            element = driver.findElement(By.id(locatorValue));
        } else if (locatorType.equalsIgnoreCase("name")) {
            element = driver.findElement(By.name(locatorValue));
        } else if (locatorType.contains("linkText")) {
            element = driver.findElement(By.partialLinkText(locatorValue));
        } else if (locatorType.equalsIgnoreCase("tagname")) {
            element = driver.findElement(By.tagName(locatorValue));
        } else if (locatorType.equalsIgnoreCase("XPATH")) {
            element = driver.findElement(By.xpath(locatorValue));
        } else if (locatorType.equalsIgnoreCase("css")) {
            element = driver.findElement(By.cssSelector(locatorValue));
        }
        return element;
    }

    public static WebElement getElementByTime(WebDriver driver, String locatorType, String locatorValue) {
        WebElement element = null;
        FluentWait<WebDriver> fw= new  WebDriverWait(driver, Duration.ofSeconds(FwConstants.Element_Wait_Time));
        fw.pollingEvery(Duration.ofSeconds(5));
        fw.ignoring(StaleElementReferenceException.class);

        if(locatorType.equalsIgnoreCase("id")){
            element = fw.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
        }else if (locatorType.equalsIgnoreCase("name")) {
            element = fw.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
        } else if (locatorType.contains("linkText")) {
            element = fw.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
        } else if (locatorType.equalsIgnoreCase("tagname")) {
            element = fw.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
        } else if (locatorType.equalsIgnoreCase("XPATH")) {
            element = fw.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
        } else if (locatorType.equalsIgnoreCase("css")) {
            element = fw.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
        }
        return element;
    }
}
