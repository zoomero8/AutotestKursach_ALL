//package tests;
//import config.AddProp;
//import config.DriverAdd;
//import config.TestListener;
//import io.qameta.allure.*;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import pageTemplate.TechnodeusPage;
//import java.util.List;
//import java.util.Map;
//
//
//@ExtendWith(TestListener.class)
//@Feature("Tests for site Tecnodeus")
//public class TechnodeusTest extends DriverAdd {
//    private TechnodeusPage technodeusPage;
//    private static final Logger logger = LoggerFactory.getLogger(TechnodeusTest.class);
//
//    @Step("Start test page Tecnodeus")
//    private void init(){
//        logger.info("Start test page Tecnodeus");
//        technodeusPage = new TechnodeusPage(driver);
//        driver.get(AddProp.getProperties("technodeus"));
//        logger.info("Page get success");
//    }
//
//    @Step("Search for a product through the search field")
//    private void searchWithField(){
//        logger.info("Product search");
//        technodeusPage.searchItem("Iphone 15 128");
//        logger.info("Product found");
//    }
//
//    @Step("Display the first 5 products, check sorting")
//    private void stepLogProduct() {
//        List<Map<String, Object>> listProduct = technodeusPage.getProductListFirst(5);
//        logger.info("Get five first products");
//        boolean isSortedDesc = true;
//        for (int i = 0; i < listProduct.size(); i++) {
//            logger.info("Product " + (i + 1) + ": " + listProduct.get(i).get("name") + " Price " + listProduct.get(i).get("price"));
//            if (i > 0 && (Integer)listProduct.get(i).get("price") > (Integer)listProduct.get(i - 1).get("price")) {
//                isSortedDesc = false;
//            }
//        }
//        if (isSortedDesc) {
//            logger.info("Prices are NOT sorted in descending order.");
//        } else {
//            logger.info("Prices are correctly sorted in descending order.");
//        }
//
//        logger.info(listProduct.size() + " products printed");
//    }
//
//    @Step("Adding a product to favorites")
//    private void addFavourite(){
//        technodeusPage.clickAddFavouriteButton();
//        logger.info("Product added to favorites");
//        driver.navigate().refresh();
//    }
//
//    @Step("Removing a favourite product")
//    private void delFavouriteProducts() {
//        technodeusPage.clickFavouritePage();
//        while (!technodeusPage.isFavoritesEmpty()) {
//            technodeusPage.clickDelFavouriteButtons();
//            driver.navigate().refresh();
//        }
//        logger.info("All favorite products removed");
//        driver.navigate().refresh();
//    }
//
//    @Step
//    private void goToPagePhones(){
//        technodeusPage.goToPhones();
//        logger.info("Navigated to the Smartphones page");
//    }
//
//    @Test
//    @Link(name = "Technodeus", url = "https://technodeus.ru/")
//    @Owner(value = "Yarovoy Denis")
//    @DisplayName("Technodeus search verification")
//    @Description("Enter \"iPhone 15 128\" in the search field, click the search button")
//    @Epic("Test for site https://technodeus.ru/ ")
//    public void test1(){
//        init();
//        searchWithField();
//    }
//
//    @Test
//    @Link(name = "Technodeus", url = "https://technodeus.ru/")
//    @Owner(value = "Yarovoy Denis")
//    @DisplayName("Check adding a product to favorites")
//    @Description("Select the first product on the page, click the heart button, go to the favorites " +
//            "page, verify the product, remove the product from favorites, refresh the page")
//    @Epic("Test for site https://technodeus.ru/ ")
//    public void test2(){
//        init();
//        addFavourite();
//        delFavouriteProducts();
//    }
//
//    @Test
//    @Link(name = "Technodeus", url = "https://technodeus.ru/")
//    @Owner(value = "Yarovoy Denis")
//    @DisplayName("Check product sorting")
//    @Description("Go to the Smartphones page, click on the sorting dropdown menu, select descending " +
//            "sorting, remember the first 5 products, compare their prices")
//    @Epic("Test for site https://technodeus.ru/ ")
//    public void test3(){
//        init();
//        goToPagePhones();
//        stepLogProduct();
//    }
//}