import applogic.ApplicationManage;
import applogic.ApplicationManager;
import applogic.CommentHelper;
import model.Category;
import model.Comment;
import pages.MainPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skir on 1/23/2017.
 */
public class WebDriverTest {

    public static void main(String[] args) {

        ApplicationManage applicationManage = new ApplicationManager();
        CommentHelper commentHelper = applicationManage.getCommentHelper();
        List<Category> expCommentCateg = new ArrayList<>();
        expCommentCateg.add(new Category("Cat0"));
        Comment newComment = new Comment(999, "text" ,true,expCommentCateg);
        commentHelper.addNewComment(newComment);
        commentHelper.sortCommentsByNumber();
        commentHelper.sortCommentsByNumber();
        Comment comment = commentHelper.getCommentFromMainPage(999);
        System.out.println(comment.getCommentId());
        System.out.println(comment.getCommentText());
        System.out.println(comment.isActive());
        System.out.println(comment.getCategories().get(4).getIntCategory());
       /* applicationManage.getNavigationManage().openMainPage().selectStatusToFilter(MainPage.Status.ACTIVE).clickApplyButton();*//*
        MainPage mainPage = applicationManage.getNavigationManage().openMainPage();
        int number = mainPage.ensurePageLoaded().getCommentRowNumberInTable(5);
       String commentText = mainPage.getCommentsTextInTable(number);
       String commentActive = mainPage.getCommentActiveStatus(number);
       String commentCategories = mainPage.getCommentsCategories(number);
        System.out.println(commentText);
        System.out.println(commentActive);*/

       /* CommentPage commentPage = mainPage.clickNewCommentButt();
        commentPage.ensurePageLoaded();
        commentPage.enterCommentNumber(2);
        commentPage.enterCommentText("44");
        commentPage.checkAvailableCategories(2,3);
        commentPage.clickAddSelectedCategories();
        commentPage.checkSelectedCategories(3);
        commentPage.clickRemoveSelectedCategories();*/
        /*CommentPage commentPage = mainPage.clickNewCommentButt();
        commentPage.enterCommentText("11");
        commentPage.enterCommentNumber(4);*/
       //pplicationManage.getNavigationManage().openMainPage().ensurePageLoaded().clickNewCommentButt().enterCommentText("11");*/
        /*applicationManage.stopApp();*/
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
