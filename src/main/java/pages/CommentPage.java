package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by skir on 1/23/2017.
 */
public class CommentPage extends Page {

    @FindBy (id = "Text")
    private WebElement commentText;
    @FindBy (id = "Number")
    private WebElement commentNumber;
    @FindBy (id = "Active")
    private WebElement active;
    @FindBy(css ="//div[id='alvailablecategories'] > .categoryitem > input[type ='checkbox']")
    private List<WebElement> availableCategories;

    @FindBy(css ="//div[id='selectedCategories'] > .categoryitem > input[type ='checkbox']")
    private List<WebElement> selectedCategories;


    @FindBy(css = "input[name='CurSelect']")
    private WebElement addSelectedCategoriesButton;
    @FindBy(css = "input[name='AllSelect']")
    private WebElement addAllCategoriesButton;

    @FindBy(css = "input[name='CurUnSelectBtn']")
    private WebElement removeSelectedCategoriesButton;

    @FindBy(css = "input[name='AllUnSelectBtn']")
    private WebElement removeAllCategoriesButton;

    @FindBy(css = "#editor-navigation > a")
    private WebElement refreshButton;

    @FindBy(css = ".buttonAsLink[value='Save']")
    private WebElement saveButton;

    @FindBy(css = ".buttonAsLink[value='Save & Return']")
    private WebElement saveAndReturnButton;


    public CommentPage(PageManager pages) {
        super(pages);
    }


    public CommentPage enterCommentText(String text) {
        commentText.sendKeys(text);
        return this;
    }

    public CommentPage enterCommentNumber(int number) {
        commentNumber.sendKeys(number + "");
        return this;
    }

    public CommentPage checkActiveCheckbox() {
        active.click();
        return this;
    }

    public CommentPage checkAvailableCategories(int category, int... categories) {

        for (WebElement element: availableCategories) {

            int value = Integer.parseInt(element.getAttribute("value"));
            if ((value -1) == category) {
                element.click();
            }
            for (int categ: categories) {
                if (categ == (value - 1)) {
                    element.click();
                }
            }
        }
        return this;
    }


    public CommentPage checkSelectedCategories(int category, int... categories) {

        for (WebElement element: selectedCategories) {
            int value = Integer.parseInt(element.getAttribute("value"));
            if ((value - 1) == category) {
                element.click();
            }
            for (int categ: categories) {
                if (categ == (value - 1)) {
                    element.click();
                }
            }
        }
        return this;
    }


    public CommentPage clickAddSelectedCategories() {
        addSelectedCategoriesButton.click();
        return this;
    }

    public CommentPage clickRemoveSelectedCategories() {
        removeSelectedCategoriesButton.click();
        return this;
    }

    public CommentPage clickAddAllCategories() {
        addAllCategoriesButton.click();
        return this;
    }

    public CommentPage clickRemoveAllCategories() {
        removeAllCategoriesButton.click();
        return this;
    }

    public CommentPage clickRefreshButton() {
        refreshButton.click();
        return this;
    }

    public CommentPage clickSaveButton() {
        saveButton.click();
        return this;
    }

    public MainPage clickSaveAndReturnButton() {
        saveAndReturnButton.click();
        return pages.mainPage;
    }

    public CommentPage checkCategoryValidation (String text)  {
        WebElement webElement = driver.findElement(By.xpath("//div[@id='errorfield' "
               + "and contains(.,'"+ text + "')]"));
        return this;
    }


}
