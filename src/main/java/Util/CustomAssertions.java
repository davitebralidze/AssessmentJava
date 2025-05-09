package Util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static WebDriverManager.WebDriverManager.getDriver;

public class CustomAssertions {

    public static void currentScreenIsSameAsPicture(String pictureFilePath) {
        boolean arePicturesSame = false;
        File screenshotFile = null;
        File tempFile = null;
        try {
            screenshotFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            tempFile = new File("temp_screenshot.png");
            Files.copy(screenshotFile.toPath(), tempFile.toPath());
            arePicturesSame = ImageComparator.compareImages(tempFile.getPath(), pictureFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(screenshotFile != null) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                tempFile.delete();
            }
        }
        if(!arePicturesSame) {
            Assert.fail("Images are not the same");
        }
    }

}
