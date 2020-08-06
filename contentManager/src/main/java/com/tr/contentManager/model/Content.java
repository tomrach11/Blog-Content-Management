package com.tr.contentManager.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class Content {
    private int id;

    private LocalDateTime createDate;

    @NotBlank(message = "Please enter title")
    @Size(max = 70, message= "Invalid title: Please enter title between 1-70 characters")
    private String title;

    @NotBlank(message = "Please enter content")
    @Size(max = 9999, message= "Invalid content: Please enter content between 1-9999 characters")
    private String content;

    @NotBlank(message = "Please enter type")
    private String type;

    @NotBlank(message = "Please enter status")
    private String status;

    private LocalDateTime scheduleDate;
    private LocalDateTime expiredDate;
    private String titlePicture;
    private User user;

    @NotNull(message = "Please enter #hashtag")
    private List<Tag> tags;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getTitlePicture() {
        return titlePicture;
    }

    public void setTitlePicture(String titlePicture) {
        this.titlePicture = titlePicture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Content)) return false;

        Content content1 = (Content) o;

        if (getId() != content1.getId()) return false;
        if (getCreateDate() != null ? !getCreateDate().equals(content1.getCreateDate()) : content1.getCreateDate() != null)
            return false;
        if (getTitle() != null ? !getTitle().equals(content1.getTitle()) : content1.getTitle() != null) return false;
        if (getContent() != null ? !getContent().equals(content1.getContent()) : content1.getContent() != null)
            return false;
        if (getType() != null ? !getType().equals(content1.getType()) : content1.getType() != null) return false;
        if (getStatus() != null ? !getStatus().equals(content1.getStatus()) : content1.getStatus() != null)
            return false;
        if (getScheduleDate() != null ? !getScheduleDate().equals(content1.getScheduleDate()) : content1.getScheduleDate() != null)
            return false;
        if (getExpiredDate() != null ? !getExpiredDate().equals(content1.getExpiredDate()) : content1.getExpiredDate() != null)
            return false;
        if (getTitlePicture() != null ? !getTitlePicture().equals(content1.getTitlePicture()) : content1.getTitlePicture() != null)
            return false;
        if (getUser() != null ? !getUser().equals(content1.getUser()) : content1.getUser() != null) return false;
        return getTags() != null ? getTags().equals(content1.getTags()) : content1.getTags() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getScheduleDate() != null ? getScheduleDate().hashCode() : 0);
        result = 31 * result + (getExpiredDate() != null ? getExpiredDate().hashCode() : 0);
        result = 31 * result + (getTitlePicture() != null ? getTitlePicture().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getTags() != null ? getTags().hashCode() : 0);
        return result;
    }
}
