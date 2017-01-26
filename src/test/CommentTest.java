import applogic.ApplicationManage;
import applogic.ApplicationManager;
import applogic.CommentHelper;
import applogic.NavigationManage;
import model.Category;
import model.Comment;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.MainPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stas on 1/25/17.
 */
public class CommentTest {

    private ApplicationManage app;

    @BeforeClass
    public void init() {
        app = new ApplicationManager();
    }

    @AfterMethod
    public void close() {
        app.stopApp();
    }

    @Test
    public void commentWithID999IsAddedTheSameCommentIsReturned () {
        List<Category> expCommentCateg = new ArrayList<>();
        expCommentCateg.add(new Category("Cat0"));
        expCommentCateg.add(new Category("Cat1"));
        Comment expComment = new Comment(999, "text", true, expCommentCateg);
        Comment newComment = new Comment(999, "text", true, expCommentCateg);
        NavigationManage navigationManage = app.getNavigationManage();
        navigationManage.openMainPage();
        CommentHelper commentHelper = app.getCommentHelper();
        commentHelper.addNewComment(newComment);
        Comment actComment = commentHelper.getCommentFromMainPage(999);
        Assert.assertTrue(actComment.equals(expComment));
    }
}
