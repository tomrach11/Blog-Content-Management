package com.tr.contentManager.controller;

import com.tr.contentManager.dao.*;
import com.tr.contentManager.model.Content;
import com.tr.contentManager.model.Role;
import com.tr.contentManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
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

    @GetMapping("/userManager")
    public String displayUserManager(Model model) {
        //set up nav bar
        List<Content> staticList = contentDao.getContentByType("static");
        model.addAttribute("staticList", staticList);

        //set up user list
        List<User> userList = userDao.readAll();
        model.addAttribute("userList", userList);

        return "userManager";
    }

    @GetMapping("/createUser")
    public String displayCreateUser(HttpServletRequest request, Model model) {
        //set up nav bar
        List<Content> staticList = contentDao.getContentByType("static");
        model.addAttribute("staticList", staticList);

        //set up form
        List<Role> roleList = roleDao.readAll();
        model.addAttribute("roleList", roleList);

        String url = request.getHeader("referer");
        model.addAttribute("returnPage", url);

        return "createUser";
    }

    @PostMapping("/createUser")
    public String performCreateUser(HttpServletRequest request, Model model) {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        //set password
        String rawPassword = request.getParameter("password");
        String encodedPassword = encodingPassword(rawPassword);
        if ( encodedPassword != user.getPassword()) {
            user.setPassword(encodedPassword);
        }
        //set role
        List<Role> allRole = roleDao.readAll();
        List<Role> roleList = new ArrayList<>();
        for (Role role : allRole) {
            if(role.getName().equals("ROLE_USER")) {
                roleList.add(role);
            }
        }
        if (request.getParameter("profilePicture").isEmpty()) {
            user.setProfilePicture("https://i.ibb.co/rskNZpT/profile-pic-1.jpg");
        } else {
            user.setProfilePicture(request.getParameter("profilePicture"));
        }
        user.setRoles(roleList);
        user.setEnable(true);

        //validation
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> errors = validate.validate(user);
        model.addAttribute("errors", errors);

        if (!errors.isEmpty()) {
            //set up nav bar
            List<Content> staticList = contentDao.getContentByType("static");
            model.addAttribute("staticList", staticList);

            //set up form
            roleList = roleDao.readAll();
            model.addAttribute("roleList", roleList);

            model.addAttribute("user", user);
            model.addAttribute("errors", errors);
            return "createUser";
        }

        userDao.create(user);

        String returnPageStr = request.getParameter("returnPage");
        String returnPage = returnPageStr.substring(21, returnPageStr.length());
        String toReturn = "redirect:" + returnPage;

        return toReturn;
    }

    @GetMapping("/editUser")
    public String editUser(HttpServletRequest request, Model model){
        //set up nav bar
        List<Content> staticList = contentDao.getContentByType("static");
        model.addAttribute("staticList", staticList);

        //set up pre filled form
        List<Role> roleList = roleDao.readAll();
        model.addAttribute("roleList", roleList);

        if(request.getParameter("id") != null) {
            //This is use when direct from userManager
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userDao.readById(id);
            model.addAttribute("user", user);
        } else {
            //This is use when direct from editProfile
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userName = auth.getName();
            User user = userDao.getUserByUsername(userName);
            model.addAttribute("user", user);
        }
        String url = request.getHeader("referer");
        model.addAttribute("returnPage", url);

        return "editUser";
    }

    @PostMapping("/editUser")
    public String performEditUser(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userDao.readById(userId);
        user.setUsername(request.getParameter("username"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        //set password
        String rawPassword = request.getParameter("password");
        String currentPassword = user.getPassword();
        if ( !rawPassword.equals(currentPassword)) {
            String encodedPassword = encodingPassword(rawPassword);
            user.setPassword(encodedPassword);
        }
        //set role
        List<Role> roleList = new ArrayList<>();
        try{
            String[] roleIdList = request.getParameterValues("role"); // if not admin this will crash
            for (String roleId : roleIdList) {
                int id = Integer.parseInt(roleId);
                Role role = roleDao.readById(id);
                roleList.add(role);
                user.setRoles(roleList);
            }
        } catch (NullPointerException ex) {

        }
        if (request.getParameter("enabled") != null) {
            boolean enable = Boolean.parseBoolean(request.getParameter("enabled"));
            user.setEnable(enable);
        }
        if (request.getParameter("profilePicture").isEmpty()) {
            user.setProfilePicture("https://i.ibb.co/rskNZpT/profile-pic-1.jpg");
        } else {
            user.setProfilePicture(request.getParameter("profilePicture"));
        }

        //validation
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> errors = validate.validate(user);
        model.addAttribute("errors", errors);

        if (!errors.isEmpty()) {
            //set up nav bar
            List<Content> staticList = contentDao.getContentByType("static");
            model.addAttribute("staticList", staticList);

            //set up form
            roleList = roleDao.readAll();
            model.addAttribute("roleList", roleList);

            model.addAttribute("user", user);
            model.addAttribute("errors", errors);
            return "editUser";
        }
        userDao.update(user);

        //setup where to return to
        String returnPageStr = request.getParameter("returnPage");  //took from a page before edit
        String returnPage = returnPageStr.substring(21, returnPageStr.length());
        String toReturn = "redirect:" + returnPage;

        return toReturn;
    }

    @GetMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("id"));
        userDao.delete(userId);

        return "redirect:/userManager";
    }

    private String encodingPassword(String password) {
        String clearTxtPw = password;
        // BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPw = encoder.encode(clearTxtPw);
        return hashedPw;
    }
}
