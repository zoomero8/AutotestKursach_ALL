//package tests;
//import io.qameta.allure.*;
//import config.AddProp;
//import config.DriverAdd;
//import config.TestListener;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import pageTemplate.YandexMarketPage6;
//import java.time.Duration;
//import java.util.List;
//import java.util.Map;
//
//
//@ExtendWith(TestListener.class)
//@Feature("Tests Yandex Market page")
//public class YandexTest extends DriverAdd {
//    private YandexMarketPage6 yandexMarketPage;
//    private static final Logger logger = LoggerFactory.getLogger(YandexTest.class);
//
//    @Step("Retrieving each product")
//    private void stepLogProduct(){
//        List<Map<String, String>> listProduct = yandexMarketPage.getProductListFirst(5);
//        logger.info("Get five first product");
//        for (int i = 0; i < listProduct.size(); i++) {
//            logger.info("Get product " + (i + 1));
//            logger.info("Name product " + listProduct.get(i).get("name") + " Price " + listProduct.get(i).get("price"));
//        }
//        logger.info(listProduct.size() + " product printed");
//    }
//
//    @Step("Page initialization")
//    private void init(){
//        logger.info("Start test page yandex market");
//        yandexMarketPage = new YandexMarketPage6(driver);
//        driver.get(AddProp.getProperties("yandexmarket"));
//        logger.info("Page get success");
//    }
//
//    @Step("Navigating to the catalog")
//    private void stepTransitionToCatologist(){
//        yandexMarketPage.clickCatalog();
//        logger.info("Pressed catalog");
//
//        yandexMarketPage.clickCatalogElementGaming();
//        logger.info("Selected element computer");
//
//        yandexMarketPage.clickCatalogElementXbox();
//        logger.info("Clicked Internal hard drives");
//    }
//
//    @Step("Adding a product to favorites")
//    private void addFavourite(){
//        yandexMarketPage.clickAddFavouriteButton();
//        logger.info("Product added to favorites");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//    }
//
//    @Step
//    private void delFavouriteProducts() {
//        yandexMarketPage.clickFavouritePage();
//        while (!yandexMarketPage.isFavoritesEmpty()) {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//            yandexMarketPage.clickDelFavouriteButtons();
//            driver.navigate().refresh();
//        }
//        logger.info("All favorite products removed");
//    }
//
//
//    @Test
//    @Link(name = "Яндекс Маркет", url = "https://market.yandex.ru")
//    @Owner(value = "Яровой Денис")
//    @DisplayName("Checking Yandex Market display")
//    @Description("Navigate to the Yandex Market page, click the catalog button, select all for gaming, " +
//            "Xbox, gaming consoles, click the heart button, go to the favorites page, remove the product, " +
//            "refresh the page.")
//    @Epic("Test for site https://market.yandex.ru ")
//    public void test(){
//        init();
//        stepTransitionToCatologist();
//        stepLogProduct();
//        addFavourite();
//        delFavouriteProducts();
//    }
//}