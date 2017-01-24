import applogic.ApplicationManage;
import applogic.ApplicationManager;

/**
 * Created by skir on 1/23/2017.
 */
public class WebDriverTest {

    public static void main(String[] args) {

        ApplicationManage applicationManage = new ApplicationManager();
       /* applicationManage.getNavigationManage().openMainPage().selectStatusToFilter(MainPage.Status.ACTIVE).clickApplyButton();*/
       applicationManage.getNavigationManage().openMainPage().clickNewCommentButt().enterCommentText("66").clickSaveButton().checkCategoryValidation("Please, select at least one category");
       /*System.setProperty("webdriver.gecko.driver","/mnt/JAVA/instALL/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://commentssprintone.azurewebsites.net/Editor/NewComment");

        PageManager pageManager = new PageManager(driver);
        pageManager.commentsPage.enterCommentNumber(1);
        pageManager.commentsPage.enterCommentText("tttttt");
        pageManager.commentsPage.checkActiveCheckbox();*/
        /*pageManager.commentsPage.checkAvailableCategories(1,2,3);*/
    }
}
