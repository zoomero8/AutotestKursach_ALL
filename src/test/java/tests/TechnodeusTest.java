package tests;
import io.qameta.allure.*;
import driver.ConfProperties;
import driver.DriverSetup;
import driver.TestListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageTemplate.YandexMarketPage6;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(TestListener.class)
@Feature("Тесты сайта Яндекс Маркет")
public class YandexTest extends DriverSetup {
    private YandexMarketPage6 yandexMarketPage;
    private static final Logger logger = LoggerFactory.getLogger(YandexTest.class);

    @Step("Получение каждого товара")
    private void stepLogProduct(){
        List<Map<String, String>> listProduct = yandexMarketPage.getProductListFirst(5);
        logger.info("Get five first product");
        for (int i = 0; i < listProduct.size(); i++) {
            logger.info("Get product " + (i + 1));
            logger.info("Name product " + listProduct.get(i).get("name") + " Price " + listProduct.get(i).get("price"));
        }
        logger.info(listProduct.size() + " product printed");
    }

//    @Step("Получение отсортированного списка")
//    private void stepSortList(){
//        yandexMarketPage.clickSort();
//        logger.info("Sorted product");
//        stepCheckSort();
//    }

//    @Step("Проверка сортировки")
//    private void stepCheckSort(){
//        List<Integer> listProductPrice = yandexMarketPage.getProductPriceListFirst(10);
//        logger.info("Get price first 10 product");
//        for(int i = 0; i<listProductPrice.size(); i++){
//            if (i + 1 >= listProductPrice.size()){
//                break;
//            }
//            assertEquals(listProductPrice.get(i+1), Math.max(listProductPrice.get(i), listProductPrice.get(i+1)));
//        }
//        logger.info("Sort correctly");
//        Allure.step("Проверка сортировкиy");
//    }

    @Step("Инициализации страницы")
    private void init(){
        logger.info("Start test page yandex market");
        yandexMarketPage = new YandexMarketPage6(driver);
        driver.get(ConfProperties.getProperties("yandexmarket"));
        logger.info("Page get success");
    }

    @Step("Переход к катологу")
    private void stepTransitionToCatologist(){
        yandexMarketPage.clickCatalog();
        logger.info("Pressed catalog");

        yandexMarketPage.clickCatalogElementGaming();
        logger.info("Selected element computer");

        yandexMarketPage.clickCatalogElementXbox();
        logger.info("Clicked Internal hard drives");
    }

    @Step("Добавление товара в избранное")
    private void addFavourite(){
        yandexMarketPage.clickAddFavouriteButton();
        logger.info("Товар добавлен в избранное");
        driver.navigate().refresh();
    }

    @Step("Переход на страницу избранного")
    private void goTofavourite(){
        yandexMarketPage.clickFavouritePage();
        logger.info("Перешли на страницу избранного");
    }

//    @Step
//    private void delFavouriteProduct(){
//        yandexMarketPage.clickDelFavouriteButton();
//        logger.info("Удалили избранный товар");
//        driver.navigate().refresh();
//    }

    @Step
    private void delFavouriteProducts() {
        // Переходим на страницу избранного
        yandexMarketPage.clickFavouritePage();
        // Цикл продолжается, пока избранное не опустеет
        while (!yandexMarketPage.isFavoritesEmpty()) {
            yandexMarketPage.clickDelFavouriteButtons(); // Метод должен удалять все видимые товары
            driver.navigate().refresh();
        }
        logger.info("Все избранные товары удалены");
        driver.navigate().refresh(); // Финальное обновление для подтверждения
    }


    @Test
    @Link(name = "Яндекс Маркет", url = "https://market.yandex.ru")
    @Owner(value = "Яровой Денис")
    @DisplayName("Проверка отображения яндекс маркета")
    @Epic("Test for site https://market.yandex.ru ")
    public void test(){
        init();
        stepTransitionToCatologist();
        stepLogProduct();
        addFavourite();
        goTofavourite();
        delFavouriteProducts();
    }
}