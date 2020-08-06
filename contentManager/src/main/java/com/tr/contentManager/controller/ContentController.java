package com.tr.contentManager.controller;

import com.tr.contentManager.dao.*;
import com.tr.contentManager.model.Comment;
import com.tr.contentManager.model.Content;
import com.tr.contentManager.model.Tag;
import com.tr.contentManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ContentController {
    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ContentDao contentDao;

    @Autowired
    TagDao tagDao;

    @Autowired
    CommentDao commentDao;

    @GetMapping({"/post"})
    public String displayPostPage(HttpServletRequest request, Model model ) {
        int postId = Integer.parseInt(request.getParameter("id"));
        Content content = contentDao.readById(postId);
        List<Comment> commentList = commentDao.getCommentByContentId(postId);
        List<Content> staticList = contentDao.getContentByType("static");
        //set #hashtag side menu
        List<Tag> tagList = tagDao.readAll();
        List<Tag> tagList1 = new ArrayList<>();
        List<Tag> tagList2 = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            if (i < tagList.size()/2) {
                tagList1.add(tagList.get(i));
            }
            if ( (i > tagList.size()/2) || (i == tagList.size()/2)){
                tagList2.add(tagList.get(i));
            }
        }
        model.addAttribute("tagList1", tagList1);
        model.addAttribute("tagList2", tagList2);


        model.addAttribute("content", content);
        model.addAttribute("commentList", commentList);
        model.addAttribute("staticList", staticList);
        return "post";
    }

    @GetMapping({"/static"})
    public String displayStaticPage(HttpServletRequest request, Model model) {
        int postId = Integer.parseInt(request.getParameter("id"));
        Content content = contentDao.readById(postId);
        List<Content> staticList = contentDao.getContentByType("static");
        //set #hashtag side menu
        List<Tag> tagList = tagDao.readAll();
        List<Tag> tagList1 = new ArrayList<>();
        List<Tag> tagList2 = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            if (i < tagList.size()/2) {
                tagList1.add(tagList.get(i));
            }
            if ( (i > tagList.size()/2) || (i == tagList.size()/2)){
                tagList2.add(tagList.get(i));
            }
        }
        model.addAttribute("tagList1", tagList1);
        model.addAttribute("tagList2", tagList2);

        model.addAttribute("content", content);
        model.addAttribute("staticList", staticList);
        return "static";
    }

    @GetMapping("/contentManager")
    public String displayContentManager(Model model) {
        //set up nav bar
//        List<Content> staticList = contentDao.getContentByType("static");
//        model.addAttribute("staticList", staticList);

        //set up content manager page
        List<Content> contentList = contentDao.readAll();
        model.addAttribute("contentList", contentList);

        return "contentManager";
    }

    @GetMapping("/editContent")
    public String displayEditContent(HttpServletRequest request, Model model) {
        //set pre filled form
        int contentId = Integer.parseInt(request.getParameter("id"));
        Content content = contentDao.readById(contentId);
        model.addAttribute("content", content);

        //set drop down list in edit's form
        List<User> userList = userDao.readAll();
        model.addAttribute("userList", userList);

        List<String> typeList = new ArrayList<>();
        typeList.add("blog");
        typeList.add("static");
        model.addAttribute("typeList", typeList);

        List<String> statusList = new ArrayList<>();
        statusList.add("private");
        statusList.add("public");
        statusList.add("in progress");
        model.addAttribute("statusList", statusList);

        List<Tag> tagList = tagDao.readAll();
        model.addAttribute("tagList", tagList);

        return "editContent";
    }

    @PostMapping("/editContent")
    public String performEditContent(HttpServletRequest request, Model model) {

        //save edited content
        int contentId = Integer.parseInt(request.getParameter("id"));
        Content content = contentDao.readById(contentId);

        content.setTitle(request.getParameter("title"));
        String statusStr = request.getParameter("status");
        if (statusStr != null) {
            content.setStatus(request.getParameter("status"));
        }
        String typeStr = request.getParameter("type");
        if (typeStr != null) {
            content.setType(request.getParameter("type"));
        }
        String userIdStr = request.getParameter("userId");
        if (userIdStr != null) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            content.setUser(userDao.readById(userId));
        }
        content.setContent(request.getParameter("content"));
        if (!request.getParameter("scheduleDate").isEmpty()) {
            LocalDate localDate = LocalDate.parse(request.getParameter("scheduleDate"));
            LocalDateTime scheduleDate = localDate.atTime(LocalTime.now());
            content.setScheduleDate(scheduleDate);
        }
        if (!request.getParameter("expiredDate").isEmpty()) {
            LocalDate localDate = LocalDate.parse(request.getParameter("expiredDate"));
            LocalDateTime expiredDate = localDate.atTime(LocalTime.now());
            content.setExpiredDate(expiredDate);
        }

        List<Tag> tagList = new ArrayList<>();
        try {
            String[] tagIdList = request.getParameterValues("tag");
            for (String tagId : tagIdList) {
                int id = Integer.parseInt(tagId);
                tagList.add(tagDao.readById(id));
            }
            content.setTags(tagList);
        } catch (NullPointerException ex) {

        }
        if (request.getParameter("titlePicture").isEmpty()) {
            content.setTitlePicture("http://placehold.it/750x300");
        } else {
            content.setTitlePicture(request.getParameter("titlePicture"));
        }


        //validation
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Content>> errors = validate.validate(content);
        model.addAttribute("errors", errors);
        if (!errors.isEmpty()) {

            //set drop down list in edit's form
            List<User> userList = userDao.readAll();
            model.addAttribute("userList", userList);

            List<String> typeList = new ArrayList<>();
            typeList.add("blog");
            typeList.add("static");
            model.addAttribute("typeList", typeList);

            List<String> statusList = new ArrayList<>();
            statusList.add("private");
            statusList.add("public");
            statusList.add("in progress");
            model.addAttribute("statusList", statusList);

            List<Tag> tagListAll = tagDao.readAll();
            model.addAttribute("tagList", tagListAll);

            model.addAttribute("errors", errors);
            model.addAttribute("content", content);

            return "editContent";
        }

        contentDao.update(content);

        //set up nav bar
        List<Content> staticList = contentDao.getContentByType("static");
        model.addAttribute("staticList", staticList);

        //set up content manager page
        List<Content> contentList = contentDao.readAll();
        model.addAttribute("contentList", contentList);

        return "redirect:/contentManager";
    }

    @GetMapping("/createContent")
    public String displayCreateContent(Model model) {
        //set up nav bar
        List<Content> staticList = contentDao.getContentByType("static");
        model.addAttribute("staticList", staticList);

        //set drop down list in form
        List<User> userList = userDao.readAll();
        model.addAttribute("userList", userList);

        List<String> typeList = new ArrayList<>();
        typeList.add("blog");
        typeList.add("static");
        model.addAttribute("typeList", typeList);

        List<String> statusList = new ArrayList<>();
        statusList.add("private");
        statusList.add("public");
        statusList.add("in progress");
        model.addAttribute("statusList", statusList);

        List<Tag> tagList = tagDao.readAll();
        model.addAttribute("tagList", tagList);

        return "createContent";
    }

    @PostMapping("/createContent")
    public String performCreateContent(HttpServletRequest request, Model model) {
        //perform create content
        Content content = new Content();
        content.setCreateDate(LocalDateTime.now());
        content.setTitle(request.getParameter("title"));
        content.setContent(request.getParameter("content"));

        //content that only admin can do
        if (request.getParameter("type") == null) {
            content.setType("blog"); //default
        } else {
            content.setType(request.getParameter("type"));
        }
        if (request.getParameter("status") == null) {
            content.setStatus("private"); //default
        } else {
            content.setStatus(request.getParameter("status"));
        }
        if (request.getParameter("userId") == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();   //default
            String username = auth.getName();
            content.setUser(userDao.getUserByUsername(username));
        } else {
            int userId = Integer.parseInt(request.getParameter("userId"));
            content.setUser(userDao.readById(userId));
        }

        if (!request.getParameter("scheduleDate").isEmpty()) {
            LocalDate localDate = LocalDate.parse(request.getParameter("scheduleDate"));
            LocalDateTime scheduleDate = localDate.atTime(LocalTime.now());
            content.setScheduleDate(scheduleDate);
        } else {
            content.setScheduleDate(LocalDateTime.now());
        }
        if (!request.getParameter("expiredDate").isEmpty()) {
            LocalDate localDate = LocalDate.parse(request.getParameter("expiredDate"));
            LocalDateTime expiredDate = localDate.atTime(LocalTime.now());
            content.setExpiredDate(expiredDate);
        }

        List<Tag> tagList = new ArrayList<>();
        try {
            String[] tagIdList = request.getParameterValues("tag");
            for (String tagId : tagIdList) {
                int id = Integer.parseInt(tagId);
                tagList.add(tagDao.readById(id));
            }
            content.setTags(tagList);
        } catch (NullPointerException ex) {

        }
        if (request.getParameter("titlePicture").isEmpty()) {
            content.setTitlePicture("http://placehold.it/750x300");
        } else {
            content.setTitlePicture(request.getParameter("titlePicture"));
        }

        //validation
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Content>> errors = validate.validate(content);
        model.addAttribute("errors", errors);
        if (!errors.isEmpty()) {
            //set drop down list in edit's form
            List<User> userList = userDao.readAll();
            model.addAttribute("userList", userList);

            List<String> typeList = new ArrayList<>();
            typeList.add("blog");
            typeList.add("static");
            model.addAttribute("typeList", typeList);

            List<String> statusList = new ArrayList<>();
            statusList.add("private");
            statusList.add("public");
            statusList.add("in progress");
            model.addAttribute("statusList", statusList);

            List<Tag> tagListAll = tagDao.readAll();
            model.addAttribute("tagList", tagListAll);

            model.addAttribute("errors", errors);
            model.addAttribute("content", content);

            return "createContent";
        }

        contentDao.create(content);

        //set up nav bar
        List<Content> staticList = contentDao.getContentByType("static");
        model.addAttribute("staticList", staticList);

        //set up content manager page
        List<Content> contentList = contentDao.readAll();
        model.addAttribute("contentList", contentList);


        return "redirect:/contentManager";
    }

    @GetMapping ("/deleteContent")
    public String deleteContent (HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        contentDao.delete(id);

        //set up content manager page
        List<Content> contentList = contentDao.readAll();
        model.addAttribute("contentList", contentList);

        return "redirect:/contentManager";
    }

    @GetMapping("/searchPost")
    public String displaySearchResult (HttpServletRequest request, Model model) {
        String idString = request.getParameter("id");
        if (idString != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            List<Content> blogList = contentDao.getContentByTag(id);
            model.addAttribute("blogList", blogList);
            //set title
            Tag tag = tagDao.readById(id);
            model.addAttribute("tag", tag);
        }
        String search = request.getParameter("search");
        if (search != null) {
            String searchText = request.getParameter("search");
            List<Content> blogList = contentDao.getContentBySearchTitle(searchText);
            model.addAttribute("blogList", blogList);
        }
        //set up nav bar
        List<Content> staticList = contentDao.getContentByType("static");
        model.addAttribute("staticList", staticList);

        //set #hashtag side menu
        List<Tag> tagList = tagDao.readAll();
        List<Tag> tagList1 = new ArrayList<>();
        List<Tag> tagList2 = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            if (i < tagList.size()/2) {
                tagList1.add(tagList.get(i));
            }
            if ( (i > tagList.size()/2) || (i == tagList.size()/2)){
                tagList2.add(tagList.get(i));
            }
        }
        model.addAttribute("tagList1", tagList1);
        model.addAttribute("tagList2", tagList2);

        return "searchPost";
    }
}
