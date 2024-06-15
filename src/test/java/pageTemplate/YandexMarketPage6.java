package pageTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YandexMarketPage6 {
    private final WebDriver driver;
    public YandexMarketPage6(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@data-baobab-name='catalog']/button")
    private WebElement buttonCatalog;

    @FindBy(xpath = "//a[.//span[text()='Все для гейминга']]")
    private WebElement catalogElementGaming;

    @FindBy(xpath = "//a[contains(@href, '/catalog--igrovye-pristavki-xbox') and text()='Игровые приставки']")
    private WebElement catalogElementXbox;

    @FindBy(xpath = "//div[@data-apiary-widget-name='@light/Organic']") // каждый Xbox с начала
    private List<WebElement> catalog;

    @FindBy(xpath = "//button[@title='Добавить в избранное']")
    private WebElement favouriteButtonXbox;

    @FindBy(xpath = "//div[@data-baobab-name='favorites'][.//div[@role='alert' and contains(text(), 'Избранное')]]")
    private WebElement favouriteMenu;

    @FindBy(xpath = "//button[@title='Удалить из избранного']")
    private WebElement delFavourite;


    public void clickCatalog(){
        buttonCatalog.click();
    }

    public void clickCatalogElementGaming(){
        Actions action = new Actions(driver);
        action.moveToElement(catalogElementGaming).build().perform();
    }

    public void clickCatalogElementXbox(){
        catalogElementXbox.click();
    }

    public void clickFavouritePage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        favouriteMenu.click();
    }

    public void clickDelFavouriteButtons() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        delFavourite.click();
    }

    public boolean isFavoritesEmpty() {
        // Проверка на наличие текста, указывающего на пустое избранное
        List<WebElement> emptyMessage = driver.findElements(By.xpath("//span[text()='Войдите в аккаунт']"));
        return !emptyMessage.isEmpty(); // Если сообщение найдено, избранное пусто
    }

    public void clickAddFavouriteButton(){
        favouriteButtonXbox.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public List<Map<String, String>> getProductListFirst(Integer count){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (int i = 0; i < count; i++) {
            WebElement product = catalog.get(i);
            WebElement name = product.findElement(By.xpath(".//h3[@data-auto='snippet-title']"));
            WebElement price = product.findElement(By.xpath(".//span[@data-auto='snippet-price-current']/span[1]"));

            HashMap<String, String> item = new HashMap<>();
            item.put("name", name.getText());
            item.put("price", price.getText());
            list.add(item);
        }
        return list;
    }
}