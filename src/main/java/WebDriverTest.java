import applogic.ApplicationManage;
import applogic.ApplicationManager;
import applogic.CommentHelper;
import model.Category;
import model.Comment;
import pages.CommentPage;
import pages.MainPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skir on 1/23/2017.
 */
public class WebDriverTest {

    public static void main(String[] args) {

        ApplicationManage applicationManage = new ApplicationManager();
       MainPage mainPage = applicationManage.getNavigationManage().openMainPage();
       mainPage.ensurePageLoaded();
        mainPage.checkCommentChecBoxinTable(1);
        mainPage.selectStatusToFilter("1");
        mainPage.selectCategoryToFilter(4);
        mainPage.selectAction("Activate");

        System.out.println(mainPage.getStatusToFilter());
        System.out.println(mainPage.getCategoryToFilter());
        System.out.println(mainPage.getAction());


//

       /* applicationManage.getNavigationManage().openMainPage();
        CommentHelper commentHelper = applicationManage.getCommentHelper();
        List<Category> newCommentCateg = new ArrayList<>();
        newCommentCateg.add(new Category("Cat0"));
        newCommentCateg.add(new Category("Cat1"));
        newCommentCateg.add(new Category("Cat2"));
        Comment newComment = new Comment(999, "text",true, newCommentCateg)*/;

      /*  List<Category> oldCommentCateg = new ArrayList<>();
        Comment oldComment = new Comment(6,"6",false, oldCommentCateg);


        commentHelper.editExistingComment(oldComment);*/
      //  commentHelper.addNewComment(newComment);
        //sort comments in ascending order
    //*    commentHelper.sortCommentsByNumber();*//*
        // sort comments in descending order*/
       /* commentHelper.sortCommentsByNumber();*/
       /* Comment comment = commentHelper.getCommentFromMainPage(19);
        System.out.println(comment.getCommentId());
        System.out.println(comment.getCommentText());
        List<Category> actCategories = comment.getCategories();
        System.out.println(actCategories.get(0).getCategory());*/
    }
}
