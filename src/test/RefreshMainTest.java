import applogic.ApplicationManage;
import applogic.ApplicationManager;
import applogic.MainHelper;
import model.Comment;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
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
    public void refreshWhenNothingEntered_Step1() {
        //Arrange
        String expecCategoryInFilter = "1";
        String expecStatusInFilter = "1";
        MainHelper commentsHelper = app.getCommentsHelper();

        commentsHelper.setCommentsWithCheckBox(0);
        commentsHelper.setCommentsWithCheckBox(4);
        commentsHelper.setCommentsWithCheckBox(9);
        commentsHelper.setCategory(1);
        commentsHelper.setStatus("1");
        commentsHelper.setApply();
        commentsHelper.sortCommentsByNumber();
        commentsHelper.setCommentsPageNumber(2);

         List<Comment> expectedComments = new ArrayList<>();
        for (int i = 0; i < commentsHelper.getCommentTableSize() ; i++) {
            expectedComments.add(commentsHelper.getCommentFromMainPage(i));
        }


        commentsHelper.refReshCommentsPage();

        List<Comment> actComments = new ArrayList<>();
        for (int i = 0; i < commentsHelper.getCommentTableSize(); i++) {
            actComments.add(commentsHelper.getCommentFromMainPage(i));
        }

        Assert.assertEquals(commentsHelper.getCategory(), expecCategoryInFilter);
        Assert.assertEquals(commentsHelper.getStatus() , expecStatusInFilter);
        Assert.assertEquals(actComments, expectedComments);

       /* List<Comment> expectedComments = new ArrayList<>();
        for (int i = 0; i < commentsHelper.getCommentTableSize() ; i++) {
            expComments.add(commentsHelper.getCommentFromMainPage(i));
        }
        //Act
        commentsHelper.refReshCommentsPage();
        //Assert
        Assert.assertEquals(commentsHelper.getCategory(), expecCategoryInFilter);
        Assert.assertEquals(commentsHelper.getStatus(), expecStatusInFilter);
        Assert.assertEquals(commentsHelper.getAction(), expecActioninFilter);
        List<Comment> actComments = new ArrayList<>();
        for (int i = 0; i < commentsHelper.getCommentTableSize(); i++) {
            actComments.add(commentsHelper.getCommentFromMainPage(i));
        }*/
      /*  Assert.assertEquals(actComments, expectedComments);*/
    }

    @Test
   public void refreshWhenCommentCheckBoxesAreSet_Step2() {
        //Arrange
        String expCategory = "1";
        String expStatus = "1";
        MainHelper commentsHelper = app.getCommentsHelper();
        commentsHelper.setCommentsWithCheckBox(0);
        commentsHelper.setCommentsWithCheckBox(5);
        commentsHelper.setCommentsWithCheckBox(9);
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
        //Act
        commentsHelper.refReshCommentsPage();
        String actCategory = commentsHelper.getCategory();
        String actStatus = commentsHelper.getStatus();
        List<Comment> actComments = new ArrayList<>();
        for (int i = 0; i < commentsHelper.getCommentTableSize(); i++) {
            actComments.add(commentsHelper.getCommentFromMainPage(i));
        }
        Assert.assertEquals(actCategory ,expCategory);
        Assert.assertEquals(actStatus ,expStatus);
        Assert.assertEquals(actComments, expComments);
        //TODO check page number!!!
    }

    @Test
    public void refreshWhenCategoryIsEntered_Step3() {
        //Arrange
        MainHelper commentsHelper = app.getCommentsHelper();
        String expecCategory = "1";
        String expecStatus = "-1";
        List<Comment> expComments = new ArrayList<>();
        for (int i = 0; i < commentsHelper.getCommentTableSize() ; i++) {
            expComments.add(commentsHelper.getCommentFromMainPage(i));
        }

        commentsHelper.setCategory(5);
        commentsHelper.setApply();
        commentsHelper.refReshCommentsPage();
        String actCategory = commentsHelper.getCategory();
        Assert.assertEquals(actCategory, expecCategory);
        for (int i = 0; i < commentsHelper.getCommentTableSize() ; i++) {
            Assert.assertFalse(commentsHelper.isCommentCheckedInTable(i));
        }

        List<Comment> actComments = new ArrayList<>();
        for (int i = 0; i < commentsHelper.getCommentTableSize() ; i++) {
            actComments.add(commentsHelper.getCommentFromMainPage(i));
        }
        Assert.assertEquals(actComments ,expComments);
    }
}
