package pages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    public MainPage selectAction(Action action) {
        Select actionDropDownSelect = new Select(actionDropDown);
        actionDropDownSelect.selectByVisibleText(action.getText());
        return this;
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

    public MainPage selectStatusToFilter(Status status) {

        Select statusDropDownSelect = new Select(statusesDropDown);
        statusDropDownSelect.selectByValue(status.getValue());
        return this;
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

    public MainPage clickNextPageIfExists() {
        try {
            WebElement nextPage = driver.findElement(By.xpath(".//*[@class='webgrid-footer']/td[1]/a[last()][contains(text(),'>')]"));
            nextPage.click();
        }
        catch (NoSuchElementException e)
        {
            //TODO
        }
        return this;
    }


    public MainPage clickPreviousPageIfExists() {
        try {
            WebElement prevtPage = driver.findElement(By.xpath(".//*[@class='webgrid-footer']/td[1]/a[1][contains(text(),'<')]"));
            prevtPage.click();
        }
        catch (NoSuchElementException e)
        {
            //TODO
        }
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
