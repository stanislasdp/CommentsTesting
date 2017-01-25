package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    public CommentPage clickDeleteCommentButt() {
        deleteComment.click();
        return pages.commentsPage;
    }

    public void selectAction(Action action) {
        Select actionDropDownSelect = new Select(actionDropDown);
        actionDropDownSelect.selectByVisibleText(action.getText());
    }

    public void selectCategoryToFilter(int category) {

        Select catDropDownSelect = new Select(categoriesDropDown);
        if ((category >= 1 && category <= 6) || category == -1) {
            catDropDownSelect.selectByIndex(category);
        } else {
            throw new IllegalArgumentException("not supported category");
        }
    }

    public void selectStatusToFilter(Status status) {

        Select statusDropDownSelect = new Select(statusesDropDown);
        statusDropDownSelect.selectByValue(status.getValue());
    }

    public void clickApplyButton() {
        applyButton.click();
    }


    public void clickSortTableByNumber() {
        numberHeader.click();
    }

    public void clickSortTableByCommentText() {
        commentTextHeader.click();
    }

    public void clickSortTableByActiveComment() {
        activeTextHeader.click();
    }

    public void clickSortTableByCategory() {
        categoryHeader.click();
    }


    public void checkCommentChecBoxinTable(int rowNumber)
    {
     commentsCheckboxes.get(rowNumber).click();
    }

    public void clickNextPageIfExists() {
        try {
            WebElement nextPage = driver.findElement(
                    By.xpath(".//*[@class='webgrid-footer']/td[1]/a[last()][contains(text(),'>')]"));
            nextPage.click();
        }
        catch (NoSuchElementException e)
        {
            //TODO
        }
    }


    public void clickPreviousPageIfExists() {
        try {
            WebElement prevtPage = driver.findElement(
                    By.xpath(".//*[@class='webgrid-footer']/td[1]/a[1][contains(text(),'<')]"));
            prevtPage.click();
        }
        catch (NoSuchElementException e)
        {
            //TODO
        }
    }


    public int getCommentRowNumberInTable(int commentID) {

        for (int i = 0; i < commentsNumbers.size() ; i++) {
            if (Integer.parseInt(commentsNumbers.get(i).getText()) == commentID) {
                return i;
            }
        }
        throw new NoSuchElementException("no such number in the current page");
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



    @Override
    public MainPage ensurePageLoaded() {
        super.ensurePageLoaded();
        wait.until(presenceOfElementLocated(By.cssSelector("#title>h1")));
        return this;
    }

    public enum Action {
        ACTIVATE {
            @Override
            String getText() {
                return "Activate";
            }
        },
        INACTIVATE {
            @Override
            String getText() {
                return "Inactivate";
            }
        },
        REMOVE_FROM_CAT {
            @Override
            String getText() {
                return "RemoveFromCategory";
            }
        };

        abstract String getText();
    }

    public enum Status {
        ALL {
            @Override
            String getValue() {
                return "-1";
            }
        },
        ACTIVE {
            @Override
            String getValue() {
                return "1";
            }
        },

        INACTIVE {
            @Override
            String getValue() {
                return "0";
            }
        };

        abstract String getValue();
    }






}
