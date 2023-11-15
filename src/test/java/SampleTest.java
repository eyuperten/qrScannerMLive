import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class SampleTest {
    String folderName;
    DateFormat df;
    private IOSDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> momentumOptions = new HashMap<String, Object>();
        momentumOptions.put("user", "eyup.erten@mobven.com");
        momentumOptions.put("token", "XEVRCfeFB0R0nIh+fOLmPTeUInT8cZWCJ8LKqO6UgDY=");
        momentumOptions.put("gw", 4063);
        capabilities.setCapability("momentum:options", momentumOptions);
        capabilities.setCapability("appium:platformName", "iOS");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:autoAcceptAlerts", true);
        capabilities.setCapability("appium:language", "en");
        capabilities.setCapability("appium:locale", "en");
        capabilities.setCapability("appium:deviceName", "");
        // capabilities.setCapability("appium:bundleId", "com.apple.mobileslideshow");
        capabilities.setCapability("appium:udid", "");
        capabilities.setCapability("appium:app", "");
        capabilities.setCapability("appium:fullReset", true);
        capabilities.setCapability("appium:noReset", false);
        capabilities.setCapability("appium:remoteDebugProxy", "gw+2000");

        driver = new IOSDriver(new URL("https://console.momentumsuite.com/gateway/wd/hub"), capabilities);
    }
    private void captureScreenShots() throws IOException {
        // Set up folder name and date format
        folderName = "screenshots"; // Change this to the desired folder name
        df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dateFormat = df.format(new Date());

        // Create folder if it doesn't exist
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Capture screenshot
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save screenshot to a file
        String screenshotFileName = "Screenshot_" + dateFormat + ".png";
        File targetFile = new File(folder, screenshotFileName);
        try {
            org.apache.commons.io.FileUtils.copyFile(screenshotFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to capture screenshot: " + e.getMessage());
        }
        System.out.println("Screenshot captured: " + targetFile.getAbsolutePath());
    }



    @Test
    public void sampleTest() throws InterruptedException, NotFoundException, IOException {
        captureScreenShots();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.activateApp("com.apple.mobileslideshow");
        captureScreenShots();
        // Assume the first picture has a specific identifier, replace the locator accordingly
        WebElement firstPicture = driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Recents\"]"));
        firstPicture.click();
        captureScreenShots();
        // Wait for the picture to load
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // Take a screenshot
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        ByteArrayInputStream bis = new ByteArrayInputStream(screenshot);
        BufferedImage image = ImageIO.read(bis);

        // Use ZXing to decode the QR code
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);



        driver.terminateApp("com.apple.mobileslideshow");
        captureScreenShots();
        // Print the decoded result
        System.out.println("QR Code Text: " + qrCodeResult.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
