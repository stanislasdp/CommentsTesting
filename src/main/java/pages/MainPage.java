package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by skir on 1/23/2017.
 */
public class MainPage extends Page {

    public MainPage (PageManager pages)  {
        super(pages);
    }

    @FindBy(id = "newbutton")
    private WebElement newComment;

    @FindBy(css = "input[value='Duplicate...']")
    private WebElement duplicateComment;

    @FindBy(css = "input[value='Edit..']")
    private WebElement editComment;

    @FindBy(css = "input[value='Delete']")
    private WebElement deleteComment;

    @FindBy(id = "commandSelect")
    private WebElement actionDropDown;

    @FindBy(id= "SelectedCateg")
    private WebElement categoryNamesDropDown;

    @FindBy(id = "SelectedStatus")
    private WebElement statusesDropDown;

    @FindBy(id = "applybutton")
    private WebElement applyButton;

    @FindBy(xpath = ".//*[@class='webgrid-header']/th[2]/a")
    private WebElement numberHeader;

    @FindBy(xpath = ".//*[@class='webgrid-header']/th[3]/a")
    private WebElement commentTextHeader;

    @FindBy(xpath = ".//*[@class='webgrid-header']/th[4]/a")
    private WebElement activeTextHeader;

    @FindBy(xpath = ".//*[@class='webgrid-header']/th[5]/a")
    private WebElement categoryHeader;

    @FindBy(id="SelectedCateg")
    private WebElement categoriesDropDown;

    @FindBy(xpath = ".//*[@class = 'webgrid-row-style' "
           + "or @class = 'webgrid-alternating-row']/td[@class='numbercolumn']")
    private List<WebElement> commentsNumbers;

    @FindBy(xpath = ".//*[@class = 'webgrid-row-style' "
           + "or @class = 'webgrid-alternating-row']/td[@class='textcolumn']")
    private List<WebElement> commentsText;

    @FindBy(xpath = ".//*[@class = 'webgrid-row-style' "
            + "or @class = 'webgrid-alternating-row']/td[@class='inactivecolumn']")
    private List<WebElement> commentsStatuses;

    @FindBy(xpath = ".//*[@class = 'webgrid-row-style' "
            + "or @class = 'webgrid-alternating-row']/td[@class='categorycolumn']")
    private List<WebElement> commentCategories;

    @FindBy(css = ".checkedcolumn>input[type='checkbox']")
    private List<WebElement> commentsCheckboxes;

    @FindBy (css = "#logindisplay>a")
    private WebElement refReshButton;

    public CommentPage clickNewCommentButt() {
        newComment.click();
        return pages.commentsPage;
    }

    public CommentPage clickDuplicateCommentButt() {
        duplicateComment.click();
        return pages.commentsPage;
    }

    public CommentPage clickEditCommentButt() {

        editComment.click();
        return pages.commentsPage;
    }

    public MainPage clickDeleteCommentButt() {
        deleteComment.click();
        return this;
    }

    public MainPage selectAction(String action) {
        Select actionDropDownSelect = new Select(actionDropDown);
        actionDropDownSelect.selectByVisibleText(action);
        return this;
    }

    public String getAction() {
        Select actionDropDownSelect = new Select(actionDropDown);
        return actionDropDownSelect.getFirstSelectedOption().getAttribute("value");
    }

    public MainPage selectCategoryToFilter(int category) {

        Select catDropDownSelect = new Select(categoriesDropDown);
        if ((category >= 1 && category <= 6) || category == -1) {
            catDropDownSelect.selectByIndex(category);
        } else {
            throw new IllegalArgumentException("not supported category");
        }
        return this;
    }

    public String getCategoryToFilter() {
        Select catDropDownSelect = new Select(categoriesDropDown);
        return catDropDownSelect.getFirstSelectedOption().getAttribute("value");
    }

    public MainPage selectStatusToFilter(String status) {

        Select statusDropDownSelect = new Select(statusesDropDown);
        statusDropDownSelect.selectByValue(status);
        return this;
    }

    public String getStatusToFilter() {
        Select statusDropDownSelect = new Select(statusesDropDown);
        return statusDropDownSelect.getFirstSelectedOption().getAttribute("value");
    }

    public MainPage clickApplyButton() {
        applyButton.click();
        return this;
    }


    public MainPage clickSortTableByNumber() {
        numberHeader.click();
        return this;
    }

    public MainPage clickSortTableByCommentText() {
        commentTextHeader.click();
        return this;
    }

    public MainPage clickSortTableByActiveComment() {
        activeTextHeader.click();
        return this;
    }

    public MainPage clickSortTableByCategory() {
        categoryHeader.click();
        return this;
    }


    public MainPage checkCommentChecBoxinTable(int rowNumber)
    {
     commentsCheckboxes.get(rowNumber).click();
        return this;
    }

    public boolean isCheckBoxinTableChecked (int rowNumber) {
        return commentsCheckboxes.get(rowNumber).isSelected();
    }

    public MainPage clickPageNumber(int rowNumber) {
        WebElement nextPage = driver.findElement(
                By.xpath(".//*[@class='webgrid-footer']/td[1]/a[contains(text(),'" + rowNumber+ "')]"));
        return this;
    }

    public MainPage clickNextPageIfExists() {

            WebElement nextPage = driver.findElement(
                    By.xpath(".//*[@class='webgrid-footer']" +
                            "/td[1]/a[last()][contains(text(),'>')]"));
            nextPage.click();
        return this;
    }


    public MainPage clickPreviousPageIfExists() {
        try {
            WebElement prevtPage = driver.findElement(
                    By.xpath(".//*[@class='webgrid-footer']/td[1]/a[1][contains(text(),'<')]"));
            prevtPage.click();

        }
        catch (NoSuchElementException e)
        {
            //TODO
        }
        return this;
    }


    public int getCommentRowNumberInTable(int commentID) {

        for (int i = 0; i < commentsNumbers.size() ; i++) {
            if (Integer.parseInt(commentsNumbers.get(i).getText()) == commentID) {
                return i;
            }
        }
        throw new NoSuchElementException("no such number in the current page");
    }

    public int getCommentNumberfromRowInTable(int rowNum) {
        return Integer.parseInt(commentsNumbers.get(rowNum).getText());
    }

    public String getCommentsTextInTable(int rowNumber) {
        return commentsText.get(rowNumber).getText();
    }

    public String getCommentActiveStatus(int rowNumber) {
        return commentsStatuses.get(rowNumber).getText();
    }

    public String getCommentsCategories(int rowNumber) {
        return commentCategories.get(rowNumber).getText();
    }

    public int getRowsCount() {
        return commentsNumbers.size();
    }

    public MainPage clickRefreshButton() {
        refReshButton.click();
        return this;
    }

    @Override
    public MainPage ensurePageLoaded() {
        super.ensurePageLoaded();
        wait.until(presenceOfElementLocated(By.cssSelector("#title>h1")));
        return this;
    }
}
