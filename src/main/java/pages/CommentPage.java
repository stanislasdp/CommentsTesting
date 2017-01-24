package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
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
    @FindBy(css ="input[id=Categories]")
    private List<WebElement> selectedCategories;

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

    public CommentPage setCategories(int category, int... categories) {
        selectedCategories.get(category).click();
        for (int cat : categories) {
            selectedCategories.get(cat).click();
        }
        return this;
    }


}
