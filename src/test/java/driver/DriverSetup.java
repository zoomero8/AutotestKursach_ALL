//package driver;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.BeforeEach;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.events.EventFiringDecorator;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.Duration;
//
//
//public abstract class DriverSetup {
//    /*
//    Для корректной работы тестов необходимо создать новый профиль в Google Chrome
//    Авторизироваться в Яндексе и Онлайн трейде
//    По ссылке chrome://version найти путь к профилю
//    Примечание: не должно быть запущенно ни одного процесса связанного с chrome в диспетчере задач
//     */
//
//    private static Logger logger = LoggerFactory.getLogger(DriverSetup.class);
//
//    public static WebDriver driver;
//    @BeforeEach
//    public void setup() {
////        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yaden\\IdeaProjects\\AutoTestKursachYarovoy\\src\\test\\resources\\chromedriver.exe");
////        WebDriver driver = new ChromeDriver();
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
////        Указать путь к папке с профилем <вставить свой по примеру>
//        options.addArguments("--user-data-dir=C:\\Users\\yaden\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
////        И указать название профиля (конечную папку) по ссылке chrome://version
//        options.addArguments("--profile-directory=Profile 1");
////        options.addArguments("headless");
//        driver = new EventFiringDecorator(new CustomEvent()).decorate(new ChromeDriver(options));
//
//
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        logger.info("Init driver success");
//    }
//}

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
        // options.addArguments("headless");
        driver = new EventFiringDecorator(new CustomEvent()).decorate(new ChromeDriver(options));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logger.info("Init driver success");
    }
}
