import applogic.ApplicationManage;
import applogic.ApplicationManager;
import applogic.CommentHelper;
import applogic.MainHelper;
import model.Category;
import model.Comment;
import pages.MainPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skir on 1/23/2017.
 */
public class WebDriverTest {

    public static void main(String[] args) {

        //some change for fetch
        ApplicationManage applicationManage = new ApplicationManager();
       MainPage mainPage = applicationManage.getNavigationManage().openMainPage();
        mainPage.ensurePageLoaded();
       MainHelper commentsHelper = applicationManage.getCommentsHelper();
       commentsHelper.addNewComment();
       CommentHelper commentHelper = applicationManage.getCommentHelper();
       Comment comment = new Comment();
       commentHelper.setCommentTextToComment(comment, "text");
       commentHelper.setCommentNumberToComment(comment, "4");
       //commentHelper.setAllCategoriesToComment(comment);

        List<Category> categoryListtoAdd = new ArrayList<>();
        categoryListtoAdd.add(new Category("Cat0"));
        categoryListtoAdd.add(new Category("Cat5"));
        categoryListtoAdd.add(new Category("Cat4"));
        categoryListtoAdd.add(new Category("Cat3"));
       commentHelper.setCategoriesToComment(comment, categoryListtoAdd);

        List<Category> categoryListtoRemove = new ArrayList<>();
        categoryListtoRemove.add(new Category("Cat5"));
        commentHelper.removeCategoriesFromComment(comment, categoryListtoRemove);
        commentHelper.removeAllCategoriesFromComment(comment);




//        mainPage.checkCommentCheckBoxInTable(1);
//        mainPage.selectStatusToFilter("1");
//        mainPage.selectCategoryToFilter(4);
//        mainPage.selectAction("Activate");
//
//        System.out.println(mainPage.getStatusToFilter());
//        System.out.println(mainPage.getCategoryToFilter());
//        System.out.println(mainPage.getAction());


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
