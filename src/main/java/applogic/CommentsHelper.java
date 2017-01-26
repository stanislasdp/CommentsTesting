package applogic;

import model.Category;
import model.Comment;
import org.openqa.selenium.NoSuchElementException;
import pages.MainPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stas on 1/26/17.
 */
public class CommentsHelper extends DriverHelper {

    public CommentsHelper(ApplicationManage applicationManage) {
        super(applicationManage.getDriver());
    }

    public void refReshCommentsPage() {
        pageManager.mainPage.clickRefreshButton();
    }

    public void setCategory(int category) {
        pageManager.mainPage.selectCategoryToFilter(category);
    }

    public String getCategory() {
       return pageManager.mainPage.getCategoryToFilter();
    }

    public void setStatus(String status) {
        pageManager.mainPage.selectStatusToFilter(status);
    }

    public String getStatus() {
       return pageManager.mainPage.getStatusToFilter();
    }

    public void setAction(String action) {
       pageManager.mainPage.selectAction(action);
    }
    public String getAction() {
       return pageManager.mainPage.getAction();
    }

    public void setApply() {
        pageManager.mainPage.clickApplyButton();
    }

    public void setCommenntsWithCheckBoxes(int rowNum) {
        pageManager.mainPage.checkCommentChecBoxinTable(rowNum);
    }


    public Comment getCommentFromMainPage(int commentRow) {
        MainPage mainPage = pageManager.mainPage;
        mainPage.ensurePageLoaded();
        int commentId = mainPage.getCommentNumberfromRowInTable(commentRow);
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

    public int getCommentTableSize() {
        return pageManager.mainPage.getRowsCount();
    }

    public void sortCommentsByNumber() {
        pageManager.mainPage.clickSortTableByNumber();
    }

    public void setCommentsPageNumber(int pageNumber) {
        pageManager.mainPage.clickPageNumber(2);
    }
}
