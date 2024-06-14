package driver;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public abstract class DriverSetup {
    private static Logger logger = LoggerFactory.getLogger(DriverSetup.class);
    public static WebDriver driver;

    @BeforeEach
    public void setup() {
        // Указание пути к локально размещенному chromedriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\Users\\yaden\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
        options.addArguments("--profile-directory=Profile 1");
        driver = new EventFiringDecorator(new CustomEvent()).decorate(new ChromeDriver(options));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        logger.info("Init driver success");
    }
}
