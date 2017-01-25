package applogic;

import model.Category;
import model.Comment;
import org.openqa.selenium.WebDriver;
import pages.CommentPage;
import pages.MainPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stas on 1/24/17.
 */
public class CommentHelper extends DriverHelper {

    private ApplicationManage applicationManage;

    public CommentHelper(ApplicationManage applicationManage) {
        super(applicationManage.getDriver());
        this.applicationManage = applicationManage;

    }

    public Comment getCommentFromMainPage(int commentId) {
        MainPage mainPage = pageManager.mainPage;

        mainPage.ensurePageLoaded();
        int commentRow = mainPage.getCommentRowNumberInTable(commentId);
        String commentText = mainPage.getCommentsTextInTable(commentRow);
        String commentStatusString = mainPage.getCommentActiveStatus(commentRow);

        boolean isCommentActive = false;
        if (commentStatusString.equals("V")) {
            isCommentActive = true;
        } else if (!commentStatusString.isEmpty()) {
            throw new IllegalArgumentException("Ilegal active status");
        }
        String[] categories = mainPage.getCommentsCategories(commentRow).split(";");
        List<Category> categorList = new ArrayList<>();
        for (String cat: categories) {
            categorList.add(new Category(cat.trim()));
        }
        return new Comment(commentId, commentText, isCommentActive, categorList);
    }

    public void addNewComment(Comment comment) {
        MainPage mainPage = applicationManage.getNavigationManage().openMainPage().ensurePageLoaded();
        CommentPage commentPage =  mainPage.clickNewCommentButt();
        commentPage.ensurePageLoaded();
        commentPage.enterCommentNumber(comment.getCommentId());
        commentPage.enterCommentText(comment.getCommentText());
        commentPage.checkActiveCheckbox();
        List<Integer> commentInt = new ArrayList<>();

        for (Category category: comment.getCategories()) {
            commentInt.add(category.getIntCategory());
        }
        //commentPage.checkAvailableCategories(commentInt);
        commentPage.clickAddAllCategories();
        commentPage.clickAddSelectedCategories();
        commentPage.clickSaveAndReturnButton();

        List<Integer> categoryNumbers = new ArrayList<>();
        for (Category category: comment.getCategories()) {
            categoryNumbers.add(category.getIntCategory());
        }
      /*  commentPage.checkAvailableCategories(categoryNumbers);*/
    }

    public void sortCommentsByNumber() {
        pageManager.mainPage.clickSortTableByNumber();
    }
}
