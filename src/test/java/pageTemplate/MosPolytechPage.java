package pageTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MosPolytechPage {
    public WebDriver driver;

    @FindBy(xpath = "/html/body/header/nav/div[1]/div[2]/div[1]/div/ul/li[3]/a")
    private WebElement linkSchedule;

    @FindBy(xpath = "//*[@id=\"bx_3777608605_2811\"]/div[3]/div/div[1]/a")
    private WebElement linkSchedulePageGroup;


    public MosPolytechPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickScheduleMenu(){
        linkSchedule.click();
    }

    public void clickLinkSchedulePageGroup(){
        String originalWindow = driver.getWindowHandle();
        linkSchedulePageGroup.click();

        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
