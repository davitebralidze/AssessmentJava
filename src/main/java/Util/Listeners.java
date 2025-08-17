package Util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static WebDriverManager.WebDriverManager.getDriver;

public class Listeners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        TestStatus.testPassed.set(false);

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

        String baseName = expectedClass + "_" + expectedMethod + "_line" + line;
        String screenshotName = baseName + ".png";
        String stackTraceName = baseName + "_StackTrace.txt";

        String reportDir = System.getProperty("user.dir") + File.separator + "FailedTestReports"
                + File.separator + timestamp;
        File destDir = new File(reportDir);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        File destScreenshot = new File(destDir, screenshotName);
        try {
            FileUtils.copyFile(src, destScreenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File destStackTrace = new File(destDir, stackTraceName);
        try (PrintWriter pw = new PrintWriter(new FileWriter(destStackTrace))) {
            pw.println("Exception Message:");
            pw.println(result.getThrowable().toString());
            pw.println();
            pw.println("Full Stack Trace:");
            result.getThrowable().printStackTrace(pw);
        } catch (IOException e) {
            e.printStackTrace();
        }

        result.setStatus(ITestResult.FAILURE);
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        TestStatus.testPassed.set(true);
    }
}