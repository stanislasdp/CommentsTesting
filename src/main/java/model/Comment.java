package model;

import java.util.List;

/**
 * Created by skir on 1/23/2017.
 */
public class Comment {
    private String commentText;
    private int commentId;
    private List<Category> categories;
    private boolean active;

    public Comment(int commId, String commText, boolean active,List<Category> categories) {
        this.commentId = commId;
        this.commentText = commText;
        this.active = active;
        this.categories = categories;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCommentText() {
        return commentText;
    }

    public int getCommentId() {
        return commentId;
    }

    public boolean isActive() {
        return active;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (active != comment.active) return false;
        if (commentText != null ? !commentText.equals(comment.commentText) : comment.commentText != null) return false;
        return categories != null ? categories.equals(comment.categories) : comment.categories == null;
    }

    @Override
    public int hashCode() {
        int result = commentText != null ? commentText.hashCode() : 0;
        result = 31 * result + commentId;
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }
}
