package Util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static WebDriverManager.WebDriverManager.getDriver;

public class Listeners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String screenshotName = result.getMethod().getMethodName() + "_" + timestamp + ".png";

        String screenshotDir = System.getProperty("user.dir") + File.separator + "FailedTestScreenshot";
        File destDir = new File(screenshotDir);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        File dest = new File(destDir, screenshotName);
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}