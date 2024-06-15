package config;
import io.qameta.allure.Allure;
import org.codehaus.plexus.util.FileUtils;
import tests.LambdaTests;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TestListener implements TestWatcher {
    private static Logger logger = LoggerFactory.getLogger(LambdaTests.class);

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment("Screenshot at the test failure point", "image/png", "png",
                ((TakesScreenshot) DriverAdd.driver).getScreenshotAs(OutputType.BYTES));
        Allure.addAttachment("Logs after test failure: ",String.valueOf(DriverAdd.driver.manage().logs().get(LogType.BROWSER).getAll()));
        DriverAdd.driver.quit();
        logger.error("Test failed");

        try {
            File srcFile = ((TakesScreenshot) DriverAdd.driver).getScreenshotAs(OutputType.FILE);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MMM-dd-HH-mm-ss");
            String pathName = "testFailed-(" + context.getDisplayName().replaceAll(" ", "-").replaceAll("[\\/\\?]", "_") + ")-" + LocalDateTime.now().format(format) + ".png";
            FileUtils.copyFile(srcFile, new File(pathName));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        DriverAdd.driver.quit();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.getLifecycle().addAttachment("Screenshot after the test passed successfully", "image/png", "png",
                ((TakesScreenshot) DriverAdd.driver).getScreenshotAs(OutputType.BYTES));
        Allure.addAttachment("Logs after the test passed successfully: ",String.valueOf(DriverAdd.driver.manage().logs().get(LogType.BROWSER).getAll()));
        DriverAdd.driver.quit();
        logger.info("Test success");
        DriverAdd.driver.quit();
    }
}