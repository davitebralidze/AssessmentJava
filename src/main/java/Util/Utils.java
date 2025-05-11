package Util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

//    public static void captureFullPageScreenshot(String outputFilePath) {
//        try {
//            JavascriptExecutor js = (JavascriptExecutor) getDriver();
//            String script = """
//        document.querySelectorAll('*').forEach(el => {
//            const style = getComputedStyle(el);
//            if (style.position === 'fixed' || style.position === 'sticky') {
//                el.style.display = 'none';
//            }
//        });
//
//        document.documentElement.style.overflow = 'hidden';
//        document.body.style.overflow = 'hidden';
//    """;
//            boolean areFloatElementsDeleted = false;
//
//            // Capture logic
//            long totalHeight = (long) js.executeScript("return document.body.scrollHeight");
//            long viewportHeight = (long) js.executeScript("return window.innerHeight");
//            int totalScreens = (int) Math.ceil((double) totalHeight / viewportHeight);
//
//            List<BufferedImage> images = new ArrayList<>();
//
//            for (int i = 0; i < totalScreens; i++) {
//                js.executeScript("window.scrollTo(0, arguments[0])", i * viewportHeight);
//                Thread.sleep(300);
//
//                File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
//                images.add(ImageIO.read(screenshot));
//
//                if(!areFloatElementsDeleted) {
//                    js.executeScript(script);
//                    areFloatElementsDeleted = true;
//                }
//
//            }
//
//            int totalImageHeight = images.stream().mapToInt(BufferedImage::getHeight).sum();
//            int imageWidth = images.get(0).getWidth();
//
//            BufferedImage stitchedImage = new BufferedImage(imageWidth, totalImageHeight, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2d = stitchedImage.createGraphics();
//
//            int yOffset = 0;
//            for (BufferedImage img : images) {
//                g2d.drawImage(img, 0, yOffset, null);
//                yOffset += img.getHeight();
//            }
//            g2d.dispose();
//
//            // Save final image
//            ImageIO.write(stitchedImage, "png", new File(outputFilePath));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (WebDriverException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void captureFullPageScreenshot(String outputFilePath) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();

            // Remove fixed/sticky elements
            String script = """
            document.querySelectorAll('*').forEach(el => {
                const style = getComputedStyle(el);
                if (style.position === 'fixed' || style.position === 'sticky') {
                    el.style.display = 'none';
                }
            });
            document.documentElement.style.overflow = 'hidden';
            document.body.style.overflow = 'hidden';
        """;

            js.executeScript(script);

            long scrollHeight = (long) js.executeScript("return document.body.scrollHeight");
            long scrollWidth = (long) js.executeScript("return document.body.scrollWidth");

            long viewportHeight = (long) js.executeScript("return window.innerHeight");
            long viewportWidth = (long) js.executeScript("return window.innerWidth");

            int verticalScreens = (int) Math.ceil((double) scrollHeight / viewportHeight);
            int horizontalScreens = (int) Math.ceil((double) scrollWidth / viewportWidth);

            List<BufferedImage> imageTiles = new ArrayList<>();

            for (int y = 0; y < verticalScreens; y++) {
                for (int x = 0; x < horizontalScreens; x++) {
                    js.executeScript("window.scrollTo(arguments[0], arguments[1])", x * viewportWidth, y * viewportHeight);
                    Thread.sleep(300); // Wait for scroll and rendering

                    File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
                    imageTiles.add(ImageIO.read(screenshot));
                }
            }

            int tileWidth = imageTiles.get(0).getWidth();
            int tileHeight = imageTiles.get(0).getHeight();

            int finalImageWidth = tileWidth * horizontalScreens;
            int finalImageHeight = tileHeight * verticalScreens;

            BufferedImage stitchedImage = new BufferedImage(finalImageWidth, finalImageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = stitchedImage.createGraphics();

            int tileIndex = 0;
            for (int y = 0; y < verticalScreens; y++) {
                for (int x = 0; x < horizontalScreens; x++) {
                    BufferedImage tile = imageTiles.get(tileIndex++);
                    g2d.drawImage(tile, x * tileWidth, y * tileHeight, null);
                }
            }
            g2d.dispose();

            ImageIO.write(stitchedImage, "png", new File(outputFilePath));

        } catch (InterruptedException | WebDriverException | IOException e) {
            throw new RuntimeException(e);
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
