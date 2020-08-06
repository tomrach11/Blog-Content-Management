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
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CommentController {

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

    @GetMapping("/addComment")
    public String addComment (Model model) {

        return "home";
    }

    @PostMapping("/addComment")
    public String addComment (HttpServletRequest request, Model model) {
        //set up post page
        int postId = Integer.parseInt(request.getParameter("postId"));
        Content content = contentDao.readById(postId);
        List<Comment> commentList = commentDao.getCommentByContentId(postId);
        List<Content> staticList = contentDao.getContentByType("static");
        model.addAttribute("content", content);
        model.addAttribute("commentList", commentList);
        model.addAttribute("staticList", staticList);
        //add new comment
        Comment comment = new Comment();
        comment.setCreateDate(LocalDateTime.now());
        comment.setComment(request.getParameter("comment"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User commentUser = userDao.getUserByUsername(userName);
        comment.setUser(commentUser);
        comment.setContent(content);
        commentDao.create(comment);


        return "redirect:/post?id=" + postId;
    }

    @GetMapping("editComment")
    public String displayEditComment (HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Comment> commentList = commentDao.getCommentByContentId(id);
        model.addAttribute("commentList", commentList);

        Content content = contentDao.readById(id);
        model.addAttribute(content);

        return "editComment";
    }

    @GetMapping("deleteComment")
    public String deleteComment (HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        int contentId = Integer.parseInt(request.getParameter("contentId"));
        commentDao.delete(id);

        //set up comment table
        List<Comment> commentList = commentDao.readAll();
        model.addAttribute("commentList", commentList);
        model.addAttribute("id", contentId);
        return "redirect:/editComment?id="+contentId;
    }
}
