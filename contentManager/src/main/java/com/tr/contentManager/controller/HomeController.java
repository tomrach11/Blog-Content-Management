package com.tr.contentManager.controller;

import com.tr.contentManager.dao.*;
import com.tr.contentManager.model.Content;
import com.tr.contentManager.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

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

    @GetMapping({"/", "/home"})
    public String displayHomePage(Model model) {
        //set up nav bar
        List<Content> staticList = contentDao.getContentByType("static");
        model.addAttribute("staticList", staticList);

        //set up post list
//        List<Content> blogList = contentDao.getContentByType("blog");
//        for (Content blog : blogList) {
//            String content = blog.getContent();
//            String previewContent = content.substring(0, 200);
//            blog.setContent(previewContent);
//        }
//        model.addAttribute("blogList", blogList);
        List<Content> blogList = contentDao.getContentByType("blog");
        model.addAttribute("blogList", blogList);

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

        return "home";
    }
}
