package com.tr.contentManager.controller;

import com.tr.contentManager.dao.*;
import com.tr.contentManager.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TagController {
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

    @GetMapping("/tagManager")
    public String displatTagManager (Model model) {
        List<Tag> tagList = tagDao.readAll();
        model.addAttribute("tagList", tagList);

        return "tagManager";
    }


    @PostMapping("/createTag")
    public String createTag (HttpServletRequest request, Model model) {
        Tag tag = new Tag();
        tag.setName(request.getParameter("tag"));
        tagDao.create(tag);

        //set up tag table
        List<Tag> tagList = tagDao.readAll();
        model.addAttribute("tagList", tagList);

        return "redirect:/tagManager";
    }

    @GetMapping("deleteTag")
    public String deleteTag (HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        tagDao.delete(id);

        //set up tag table
        List<Tag> tagList = tagDao.readAll();
        model.addAttribute("tagList", tagList);

        return "redirect:/tagManager";
    }


}
