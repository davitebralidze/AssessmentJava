package Util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import static WebDriverManager.WebDriverManager.getDriver;

public class CustomAssertions {

    public static void currentPartOfPageScreenIsSameAsPicture(String pictureFilePath) {
        boolean arePicturesSame = ImageComparator.compareImages(String.valueOf(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE).toPath()), pictureFilePath);
        if(!arePicturesSame) {
            Assert.fail("Images are not the same");
        }
    }

}
