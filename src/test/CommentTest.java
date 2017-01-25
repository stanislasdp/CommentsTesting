import applogic.ApplicationManage;
import applogic.ApplicationManager;
import applogic.CommentHelper;
import model.Category;
import model.Comment;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.MainPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stas on 1/25/17.
 */
public class CommentTest {

    private static ApplicationManage app;

    @BeforeClass
    public void init() {
        app = new ApplicationManager();
    }

    @AfterClass
    public void close() {
        app.stopApp();
    }


    @Test
    public void commentWithID999IsAddedTheSameCommentIsReturned () {
        List<Category> expCommentCateg = new ArrayList<>();
        expCommentCateg.add(new Category("Cat0"));
        expCommentCateg.add(new Category("Cat1"));
        expCommentCateg.add(new Category("Cat2"));
        expCommentCateg.add(new Category("Cat3"));
        expCommentCateg.add(new Category("Cat4"));
        expCommentCateg.add(new Category("Cat5"));
        Comment expComment = new Comment(999, "text" ,true, expCommentCateg);
        Comment newComment = new Comment(999, "text" ,true, expCommentCateg);
        CommentHelper commentHelper = app.getCommentHelper();
        commentHelper.addNewComment(newComment);
        commentHelper.sortCommentsByNumber();
        commentHelper.sortCommentsByNumber();
        Comment actComment = commentHelper.getCommentFromMainPage(999);
        Assert.assertTrue(actComment.equals(expComment));


    }
}
