package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    public static String getPropertyValue(String filename, String key) throws FileNotFoundException {
        String filelocation ="actitime_testdata"; // folder name
        FileInputStream fis = new FileInputStream(filelocation +"\\" + filename);
        Properties p = new Properties();
        try {
            p.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return p.getProperty(key);
    }
}
