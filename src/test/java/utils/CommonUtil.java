package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtil {

    public static String getCurrentDate() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd-hh_mm_ss"));
        return date;
    }

    public static void takeScreenshot(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File des = new File("actitime_screenshots\\" + getCurrentDate() + ".png");
        try {
            System.out.println(1);
            FileUtils.copyFile(src, des);
            System.out.println(2);
        } catch (IOException e) {
            System.out.println(3);
            throw new RuntimeException(e);
        }
    }

    public static void takeScreenshort(WebElement element) {
        TakesScreenshot ts = (TakesScreenshot) element;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File des = new File("actitime_screenshots\\" + getCurrentDate() + ".png");
        try {
            FileUtils.copyFile(src, des);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void pauseExecution() {
        try {
            String time = PropertyFileReader.getPropertyValue("config.properties", "pause_time_in_seconds");
            Thread.sleep(Integer.valueOf(time) * 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


