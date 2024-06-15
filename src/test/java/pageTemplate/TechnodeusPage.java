package pageTemplate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TechnodeusPage {
    private final WebDriver driver;

    public TechnodeusPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@placeholder='Поиск по каталогу']")
    private WebElement searchForm;

    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'btn-primary')]/i[contains(@class, 'fa-search')]/..")
    private WebElement searchButton;

    @FindBy(xpath = "//a[contains(@class, 'favorites-not-added')]//i[contains(@class, 'fa-heart')]")
    private WebElement favouriteButton;

    @FindBy(xpath = "//div[contains(@class, 'product_card-cell')]") // каждый телефон с начала
    private List<WebElement> catalog;

    @FindBy(xpath = "//a[@href='/favorites' and contains(., 'Избранное')]")
    private WebElement favouriteButtonMenu;

    @FindBy(xpath = "//a[contains(@class, 'favorites-added') and .//i[contains(@class, 'fa-trash')]]")
    private WebElement delFavouriteButton;

    @FindBy(xpath = "//a[text()='Смартфоны']")
    private WebElement phoneButton;

    @FindBy(xpath = "//select[@id='collection_order']/option[@value='descending_price']")
    private WebElement topToLowFilter;

    public void searchItem(String query) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchForm));
        searchForm.clear();
        searchForm.sendKeys(query);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    public void goToPhones(){
        phoneButton.click();
        topToLowFilter.click();
    }

    public void clickFavouritePage(){
        favouriteButtonMenu.click();
    }

    public void clickDelFavouriteButtons() {
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//a[contains(@class, 'favorites-added') and .//i[contains(@class, 'fa-trash')]]"));
        for (WebElement button : deleteButtons) {
            button.click();
        }
    }

    public boolean isFavoritesEmpty() {
        // Проверка на наличие кнопок удаления из избранного
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//a[contains(@class, 'favorites-added') and .//i[contains(@class, 'fa-trash')]]"));

        // Избранное считается пустым, если не найдено ни одной кнопки удаления
        return deleteButtons.isEmpty();
    }

    public void clickAddFavouriteButton(){
        favouriteButton.click();
    }

    public List<Map<String, Object>> getProductListFirst(Integer count) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < count && i < catalog.size(); i++) {
            WebElement product = catalog.get(i);
            WebElement name = product.findElement(By.xpath(".//a[contains(@class, 'title')]")); // Используйте относительный XPath
            WebElement price = product.findElement(By.xpath(".//div[contains(@class, 'product_card_controls')]//var[contains(@class, 'price')]")); // Используйте относительный XPath

            HashMap<String, Object> item = new HashMap<>();
            item.put("name", name.getText());

            // Извлечение только чисел из строки цены и преобразование в Integer
            String priceText = price.getText().replaceAll("[^\\d]", ""); // Удаляем все кроме цифр
            Integer priceValue = Integer.parseInt(priceText); // Преобразуем строку в Integer
            item.put("price", priceValue);

            list.add(item);
        }
        return list;
    }


    public List<Integer> getProductPriceListFirst(Integer count) {
        List<Integer> list = new ArrayList<>();
        // Загрузка элементов карточек продукта
        List<WebElement> catalog = driver.findElements(By.xpath("//div[contains(@class, 'product_card-cell')]"));

        // Убедитесь, что запрос не превышает количество найденных элементов
        int effectiveCount = Math.min(count, catalog.size());

        for (int i = 0; i < effectiveCount; i++) {
            WebElement product = catalog.get(i);
            try {
                // Получение текста элемента с ценой и удаление всех нечисловых символов
                String priceText = product.findElement(By.xpath(".//div[contains(@class, 'product_card_controls')]//var[contains(@class, 'price')]")).getText().replaceAll("[^\\d]", "");
                // Преобразование очищенной строки в целое число
                int price = Integer.parseInt(priceText);
                list.add(price);
            } catch (NumberFormatException e) {
                // Логирование или другие действия по обработке ошибки преобразования строки в число
                System.err.println("Ошибка преобразования цены в число для элемента " + i + ": " + e.getMessage());
            }
        }
        return list;
    }


}