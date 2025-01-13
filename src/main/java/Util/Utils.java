package Util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class Utils {
    public static void deleteAllureReports() {
        String folderPath = System.getProperty("user.dir") + "\\allure-results";
        File folderToDelete = new File(folderPath);
        if (folderToDelete.exists()) {
            try {
                deleteFolder(folderToDelete);
                System.out.println("Allure reports cleared successfully.");
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
}
