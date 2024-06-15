package tests;
import io.qameta.allure.*;
import config.AddProp;
import config.DriverAdd;
import config.TestListener;
import pageTemplate.MosPolytechPage;
import pageTemplate.MosPolytechSchedulePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(TestListener.class)
@Feature("Tests Moscow Polytech")
public class MosPolytechTests extends DriverAdd {

    public static MosPolytechPage mosPolytechPage;
    public static MosPolytechSchedulePage mosPolytechSchedulePage;
    private static final Logger logger = LoggerFactory.getLogger(MosPolytechTests.class);

    @Test
    @Link(name = "Moscow Polytech", url = "https://mospolytech.ru/")
    @Owner(value = "Yarovoy Denis")
    @DisplayName("Checking the schedule display on the Moscow Polytechnic website")
    @Description("Go to the Polytech website, click on the schedule, click on the schedule again, " +
            "go to the schedule page, enter the group, select the group, check the highlight of the weekday.")
    @Epic("Test for site https://mospolytech.ru/ ")
    public void test(){
        logger.info("Start test page mospolytech");
        mosPolytechPage = new MosPolytechPage(driver);
        driver.get(AddProp.getProperties("mospolytech"));
        logger.info("Page get success");

        mosPolytechPage.clickScheduleMenu();
        logger.info("Click button schedule");

        mosPolytechPage.clickLinkSchedulePageGroup();
        logger.info("Click button schedule group");

        mosPolytechSchedulePage = new MosPolytechSchedulePage(driver);
        driver.manage().deleteCookieNamed("group");
        driver.navigate().refresh();
        mosPolytechSchedulePage.inputGroup("221-361");
        logger.info("Input group");

        assertTrue(mosPolytechSchedulePage.checkListGroups("221-361"));
        logger.info("Group list checked");

        mosPolytechSchedulePage.clickGroup("221-361");
        logger.info("Group selected");

        int numDay = getDayWeekNumber();
        if (numDay == 7){
            logger.info("The day is not chosen because it is Sunday");
        }else {
            assertTrue(mosPolytechSchedulePage.checkSelectedWeekDay(numDay));
            logger.info("Check selected week day");
        }
    }

    private int getDayWeekNumber(){
        return LocalDate.now().getDayOfWeek().getValue();
    }
}
