import applogic.ApplicationManage;
import applogic.ApplicationManager;
import applogic.CommentHelper;
import applogic.MainHelper;
import model.Comment;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by stas on 1/28/17.
 */
public class PagingTest {

    private ApplicationManage app;

    @BeforeClass
    public void init() {
        app = new ApplicationManager();
        app.getNavigationManage().openMainPage();
    }

    @Test
    public void pagingTest() {
        MainHelper mainHelp = app.getCommentsHelper();
        mainHelp.setCommentsPageNumber(2);
        int actPageNumber = mainHelp.getCurrentPageNumber();
        Assert.assertEquals(actPageNumber ,2);
        mainHelp.setPreviousPage();
        actPageNumber = mainHelp.getCurrentPageNumber();
        Assert.assertEquals(actPageNumber, 1);
        Assert.assertFalse(mainHelp.isPreviousPageExists());
        mainHelp.setNextPage();
        actPageNumber = mainHelp.getCurrentPageNumber();
        Assert.assertEquals(actPageNumber , 2);
        mainHelp.setCommentsWithCheckBox(5);
        Comment getComment = mainHelp.getCommentFromMainPage(5);
        mainHelp.duplicateComment();
        CommentHelper commHelp = app.getCommentHelper();
        commHelp.setCommentTextToComment(getComment,"Some Text");
        commHelp.setCommentNumberToComment(getComment,"999");
        commHelp.saveCommentAndReturnToMainPage();
        mainHelp.setCommentsPageNumber(4);
        mainHelp.setCommentsWithCheckBox(0);
        mainHelp.deleteCommentAndConfirm();
        Assert.assertFalse(mainHelp.isPageExists(4));
    }
}
