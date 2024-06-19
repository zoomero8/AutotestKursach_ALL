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
//import pageTemplate.ReqresPage;
//
//@ExtendWith(TestListener.class)
//@Feature("Check API and UI ReqresIn")
//public class ReqresFrontTests extends DriverAdd {
//
//    private ReqresPage reqresInPage;
//    private static final Logger logger = LoggerFactory.getLogger(ReqresFrontTests.class);
//
//    @Test
//    @Link(name = "Reqres Front Tests", url = "https://reqres.in/")
//    @Owner(value = "Yarovoy Denis")
//    @DisplayName("Check API and UI responses for various endpoints")
//    @Story("Complete verification of API and UI responses")
//    @Description("This test verifies that the results of button presses correspond to API responses")
//    public void test() {
//        init();
//        compareUIAndAPI("List users", "get");
//        compareUIAndAPI("Single user", "get");
//        compareUIAndAPI("Single user not found", "get");
//        compareUIAndAPI("List <resource>", "get");
//        compareUIAndAPI("Single <resource>", "get");
//        compareUIAndAPI("Single <resource> not found", "get");
//        compareUIAndAPI("Create", "post");
//        compareUIAndAPI("Update", "put");
//        compareUIAndAPI("Update", "patch");
//        compareUIAndAPI("Delete", "delete");
//        compareUIAndAPI("Register - successful", "post");
//        compareUIAndAPI("Register - unsuccessful", "post");
//        compareUIAndAPI("Login - successful", "post");
//        compareUIAndAPI("Login - unsuccessful", "post");
//        compareUIAndAPI("Delayed response", "get");
//    }
//
//    @Step("Start test page Reqres")
//    private void init(){
//        logger.info("Start test page Reqres");
//        reqresInPage = new ReqresPage(driver);
//        driver.get(AddProp.getProperties("reqres"));
//        logger.info("Page get success");
//    }
//
//    @Step("Verify API response for button with HTTP method")
//    private void compareUIAndAPI(String buttonName, String httpMethod) {
//        Allure.step("Verify API response for " + buttonName + " with method " + httpMethod.toUpperCase(), () -> {
//            try {
//                reqresInPage.clickOnButtonAndCheckAPI(buttonName, httpMethod);
//                logger.info("Verified response for '{}', corresponding to API", buttonName);
//            } catch (AssertionError e) {
//                logger.error("Test failed: Verify API responses for various endpoints - {}", buttonName, e);
//                throw e;
//            }
//        });
//    }
//}
