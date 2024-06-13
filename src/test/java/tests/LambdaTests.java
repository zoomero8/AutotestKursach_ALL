package tests;
import io.qameta.allure.*;
import driver.ConfProperties;
import driver.DriverSetup;
import pageTemplate.LambdaPage;
import driver.TestListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestListener.class)
@Feature("Tests Lambda sample app")
public class LambdaTests extends DriverSetup {
    public static LambdaPage lambdaPage;
    private static final Logger logger = LoggerFactory.getLogger(LambdaTests.class);

    @Step("Step checking the list item")
    public void checkItem(Integer itemNum){
        logger.info("Item {} start check", itemNum);
        assertFalse(lambdaPage.isTickBox(itemNum));
        logger.info("Item not crossed out");

        lambdaPage.selectItem(itemNum);
        logger.info("Item clicked");

        assertTrue(lambdaPage.isTickBox(itemNum));
        logger.info("Item crossed out");

        assertEquals(lambdaPage.getNumCheckboxes(), (lambdaPage.getSizeList()-itemNum-1) + " of " + lambdaPage.getSizeList() + " remaining");
        logger.info("Label remaining checked");

        logger.info("Item checked " + itemNum);
    }

    @Test
    @Link(name = "Lambda Sample App", url = "https://lambdatest.github.io/sample-todo-app/")
    @Owner(value = "Yarovoy Denis")
    @DisplayName("Checking the functionality of the Lambda website")
    @Description("Go to the website, click on each item, create a new item, verify it.")
    @Epic("Test for site https://lambdatest.github.io/sample-todo-app/ ")
    public void test(){
        logger.info("Start test page lambda sample app");
        lambdaPage = new LambdaPage(driver);

        driver.get(ConfProperties.getProperties("lambdaPage"));
        logger.info("Page get success");

        assertEquals(lambdaPage.getHeadText(), "LambdaTest Sample App");
        logger.info("Text headPage equals LambdaTest Sample App");

        for (int i = 0; i< lambdaPage.getSizeList(); i++) {
           checkItem(i);
        }
        logger.info("All item  checked");

        lambdaPage.addNewElement("Test");
        logger.info("New item created with name 'Test'");

        assertFalse(lambdaPage.isTickBox(lambdaPage.getSizeList()-1));
        logger.info("Item not crossed out");

        assertEquals(lambdaPage.getNumCheckboxes(), "1 of " + lambdaPage.getSizeList() + " remaining");
        logger.info("Label remaining checked");

        checkItem(lambdaPage.getSizeList() - 1);
        logger.info("Last item checked");
    }
}
