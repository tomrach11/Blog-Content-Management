package com.tr.contentManager.model;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private LocalDateTime createDate; 
    private String comment;
    private User user; 
    private Content content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment1 = (Comment) o;

        if (getId() != comment1.getId()) return false;
        if (getCreateDate() != null ? !getCreateDate().equals(comment1.getCreateDate()) : comment1.getCreateDate() != null)
            return false;
        if (getComment() != null ? !getComment().equals(comment1.getComment()) : comment1.getComment() != null)
            return false;
        if (getUser() != null ? !getUser().equals(comment1.getUser()) : comment1.getUser() != null) return false;
        return getContent() != null ? getContent().equals(comment1.getContent()) : comment1.getContent() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        return result;
    }
}
