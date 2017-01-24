import applogic.ApplicationManage;
import applogic.ApplicationManager;
import pages.MainPage;

/**
 * Created by skir on 1/23/2017.
 */
public class WebDriverTest {

    public static void main(String[] args) {

        ApplicationManage applicationManage = new ApplicationManager();
        applicationManage.getNavigationManage().openMainPage().selectStatusToFilter(MainPage.Status.ACTIVE).clickApplyButton();
       /*System.setProperty("webdriver.gecko.driver","/mnt/JAVA/instALL/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://commentssprintone.azurewebsites.net/Editor/NewComment");

        PageManager pageManager = new PageManager(driver);
        pageManager.commentsPage.enterCommentNumber(1);
        pageManager.commentsPage.enterCommentText("tttttt");
        pageManager.commentsPage.checkActiveCheckbox();*/
        /*pageManager.commentsPage.setCategories(1,2,3);*/
    }
}
