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

        String expectedClass = result.getTestClass().getRealClass().getSimpleName();
        String expectedMethod = result.getMethod().getMethodName();
        int line = 0;
        for (StackTraceElement element : result.getThrowable().getStackTrace()) {
            String className = element.getClassName();
            String methodName = element.getMethodName();
            if (className.endsWith(expectedClass) && methodName.equals(expectedMethod)) {
                line = element.getLineNumber();
                break;
            }
        }

        String screenshotName = result.getTestClass().getRealClass().getSimpleName() +"_"+ result.getMethod().getMethodName() + "_" + "line:" + line + "_" +timestamp + ".png";

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