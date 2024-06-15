package config;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public abstract class DriverAdd {
    private static Logger logger = LoggerFactory.getLogger(DriverAdd.class);
    public static WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\Users\\yaden\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
        options.addArguments("--profile-directory=Profile 1");
        driver = new EventFiringDecorator(new ClickLog()).decorate(new ChromeDriver(options));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        logger.info("Init driver success");
    }
}
