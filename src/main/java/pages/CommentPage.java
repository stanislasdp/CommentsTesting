package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

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
    @FindBy(css ="div[id='alvailablecategories'] > .categoryitem > input[type ='checkbox']")
    private List<WebElement> availableCategories;

    @FindBy(css ="div[id='selectedCategories'] > .categoryitem > input[type ='checkbox']")
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


    public void enterCommentText(String text) {
        commentText.sendKeys(text);
    }

    public void enterCommentNumber(int number) {
        commentNumber.sendKeys(number + "");
    }

    public void checkActiveCheckbox() {
        active.click();
    }

    public void checkAvailableCategories(List<Integer> categories) {

        for (WebElement element: availableCategories) {

            int value = Integer.parseInt(element.getAttribute("value"));
            for (int categ: categories) {
                if (categ == (value)) {
                    element.click();
                }
            }
        }
    }


    public void checkSelectedCategories(List<Integer> categories) {

        for (WebElement element: selectedCategories) {
            int value = Integer.parseInt(element.getAttribute("value"));
            for (int categ: categories) {
                if (categ == (value)) {
                    element.click();
                }
            }
        }
    }

    public void clickAddSelectedCategories() {
        addSelectedCategoriesButton.click();
    }

    public void clickRemoveSelectedCategories() {
        removeSelectedCategoriesButton.click();
    }

    public void clickAddAllCategories() {
        addAllCategoriesButton.click();
    }

    public void clickRemoveAllCategories() {
        removeAllCategoriesButton.click();
    }

    public void clickRefreshButton() {
        refreshButton.click();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public MainPage clickSaveAndReturnButton() {
        saveAndReturnButton.click();
        return pages.mainPage;
    }

    public void checkCategoryValidation (String text)  {
        WebElement webElement = driver.findElement(By.xpath("//div[@id='errorfield' "
               + "and contains(.,'"+ text + "')]"));

    }

    @Override
    public CommentPage ensurePageLoaded() {
        super.ensurePageLoaded();
        //wait until comment text field will be present
        wait.until(presenceOfElementLocated(By.cssSelector("#Number")));
        return this;
    }
}
