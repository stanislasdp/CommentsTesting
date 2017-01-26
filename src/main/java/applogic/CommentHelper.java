package applogic;

import model.Category;
import model.Comment;
import org.openqa.selenium.NoSuchElementException;
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

    public CommentHelper(ApplicationManage applicationManage) {
        super(applicationManage.getDriver());
    }

    public Comment getCommentFromMainPage(int commentId) {
        MainPage mainPage = pageManager.mainPage;
        mainPage.ensurePageLoaded();
        boolean isCommentRowFound = false;
        int commentRow = 0 ;
        while (!isCommentRowFound) {
            try {
                commentRow = mainPage.getCommentRowNumberInTable(commentId);
                isCommentRowFound = true;
            } catch (NoSuchElementException e) {
                mainPage.clickNextPageIfExists();
            }
        }
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
        MainPage mainPage = pageManager.mainPage;
        mainPage.clickNewCommentButt();
        CommentPage commentPage =  pageManager.commentsPage;
        commentPage.ensurePageLoaded();
        commentPage.enterCommentNumber(comment.getCommentId())
                .enterCommentText(comment.getCommentText()).
                checkActiveCheckbox();

        List<Integer> commentInt = new ArrayList<>();
        for (Category category: comment.getCategories()) {
            commentInt.add(category.getIntCategory());
        }
        commentPage.checkAvailableCategories(commentInt)
                    .clickAddSelectedCategories()
                    .clickSaveAndReturnButton();

        List<Integer> categoryNumbers = new ArrayList<>();
        for (Category category: comment.getCategories()) {
            categoryNumbers.add(category.getIntCategory());
        }
        commentPage.checkAvailableCategories(categoryNumbers);
    }

  /*  public void editExistingComment(Comment comment) {
        MainPage mainPage = pageManager.mainPage;
        mainPage.ensurePageLoaded();
        Comment foundComment = getCommentFromMainPage(comment.getCommentId());
       int commentRow = mainPage.getCommentRowNumberInTable(foundComment.getCommentId());
        mainPage.checkCommentChecBoxinTable(commentRow);
        mainPage.clickEditCommentButt();
        CommentPage commentPage = pageManager.commentsPage;
        commentPage.enterCommentText(comment.getCommentText());
        if ((comment.isActive() && !foundComment.isActive()) ||
                (!comment.isActive() && foundComment.isActive())) {
            commentPage.checkActiveCheckbox();
        }
        List<Category> commentFoundCategories = foundComment.getCategories();
        List<Category> commentCategories = comment.getCategories();
        //TODO

    }*/

    public void refreshCommentsPage() {

        pageManager.commentsPage.clickRefreshButton();
    }

    public void saveComment() {
        pageManager.commentsPage.clickSaveButton();
    }

    public void saveCommentAndReturnToMainPage() {
        pageManager.commentsPage.clickSaveAndReturnButton();
    }


    public void sortCommentsByNumber() {
        pageManager.mainPage.clickSortTableByNumber();
    }

  /*  public void selectOnlyActiveComments() {
        pageManager.mainPage.selectStatusToFilter(MainP);
    }

    public void selectOnlyInactiveComments() {
        pageManager.mainPage.selectStatusToFilter(MainPage.Status.INACTIVE);
    }
    public void selectActiveAndInactiveComments() {
        pageManager.mainPage.selectStatusToFilter(MainPage.Status.ALL);
    }*/
}
