package config;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;

import static config.DriverAdd.driver;

public class ClickLog implements WebDriverListener {
    protected static WebDriverWait wait;

    public ClickLog(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Override
    public void beforeClick(WebElement element) {
        StringBuilder message = new StringBuilder("Click on ").append(element.getText()).append(" (").append(element.getTagName()).append(".").append(element.getAttribute("class")).append(")");
        Allure.step(message.toString());
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        StringBuilder message = new StringBuilder("Input ").append(Arrays.toString(keysToSend)).append(" in ").append(element.getTagName()).append(".").append(element.getAttribute("class"));
        Allure.step(message.toString());
    }
}