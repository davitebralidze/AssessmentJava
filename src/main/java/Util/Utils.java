package Util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

import static WebDriverManager.WebDriverManager.getDriver;

public class Utils {
    public static void deleteAllureReports() {
        String folderPath = System.getProperty("user.dir") + "\\allure-results";
        File folderToDelete = new File(folderPath);
        if (folderToDelete.exists()) {
            try {
                deleteFolder(folderToDelete);
            } catch (SecurityException ignored) {
            }
        }
    }

    public static void deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteFolder(file);
                }
            }
        }
        if (!folder.delete()) {
            System.out.println("Failed to clear folder: " + folder);
        }
    }

    public static void takeScreenshot(String outputFilePath) {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        File destination = new File(outputFilePath);
        try {
            FileHandler.copy(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
