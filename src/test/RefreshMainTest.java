import applogic.ApplicationManage;
import applogic.ApplicationManager;
import applogic.CommentsHelper;
import jdk.nashorn.internal.ir.annotations.Ignore;
import model.Comment;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by stas on 1/26/17.
 */
public class RefreshMainTest {
    private ApplicationManage app;

    @BeforeClass
    public void init() {
        app = new ApplicationManager();
        app.getNavigationManage().openMainPage();
    }

    @AfterMethod
    public void refreshWindow() {
        app.getDriver().navigate().refresh();
    }

    @AfterClass
    public void close() {
        app.stopApp();
    }



    @Test
    public void refreshWhenNothingEntered() {
        String expecCategoryInFilter = "-1";
        String expecStatusInFilter = "-1";
        String expecActioninFilter = "None";
        CommentsHelper commentsHelper = app.getCommentsHelper();
        List<Comment> expComments = new ArrayList<>();
        for (int i = 0; i < commentsHelper.getCommentTableSize() ; i++) {
            expComments.add(commentsHelper.getCommentFromMainPage(i));
        }

        commentsHelper.refReshCommentsPage();
        Assert.assertEquals(commentsHelper.getCategory(), expecCategoryInFilter);
        Assert.assertEquals(commentsHelper.getStatus(), expecStatusInFilter);
        Assert.assertEquals(commentsHelper.getAction(), expecActioninFilter);
        List<Comment> actComments = new ArrayList<>();
        for (int i = 0; i < commentsHelper.getCommentTableSize(); i++) {
            actComments.add(commentsHelper.getCommentFromMainPage(i));
        }
        Assert.assertEquals(actComments ,expComments);
    }

    @Test
   public void refreshWhenCommentCheckBoxesAreSet() {
        CommentsHelper commentsHelper = app.getCommentsHelper();
        commentsHelper.setCommenntsWithCheckBoxes(0);
        commentsHelper.setCommenntsWithCheckBoxes(5);
        commentsHelper.setCommenntsWithCheckBoxes(9);
        commentsHelper.setCategory(1);
        commentsHelper.setStatus("1");
        commentsHelper.setApply();
       // commentsHelper.setAction("Activate");
        commentsHelper.sortCommentsByNumber();
        commentsHelper.setCommentsPageNumber(2);

        List<Comment> expComments = new ArrayList<>();
        for (int i = 0; i < commentsHelper.getCommentTableSize() ; i++) {
            expComments.add(commentsHelper.getCommentFromMainPage(i));
        }

        commentsHelper.refReshCommentsPage();
        String expCategory = "1";
        String expStatus = "1";

        String actCategory = commentsHelper.getCategory();
        String actStatus = commentsHelper.getStatus();
        List<Comment> actComments = new ArrayList<>();
        for (int i = 0; i < commentsHelper.getCommentTableSize(); i++) {
            actComments.add(commentsHelper.getCommentFromMainPage(i));
        }
        Assert.assertEquals(actCategory ,expCategory);
        Assert.assertEquals(actCategory ,expStatus);
        Assert.assertEquals(actComments, expComments);
    }

}
