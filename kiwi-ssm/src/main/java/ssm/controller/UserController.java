package ssm.controller;

import ssm.model.User;
import ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/editUserInfo",method = RequestMethod.GET)
    public String editUserInfoPage(HttpSession session) {
        User user = (User) session.getAttribute("activeUser");
        if (user.getId()!=null){
            //避免登录之后可能改变了个人情报(消费,收藏),重新搜索
            User updatedUser = userService.findUserById(user.getId());
            if (!user.equals(updatedUser)) {
                session.setAttribute("activeUser",user);
            }
        }

        return "userInfo";

    }

    @RequestMapping(value = "/editUserInfo",method = RequestMethod.POST)
    public String editUserInfo(HttpSession session,User user){
        //may update username and avatar
        User sessionUser = (User) session.getAttribute("activeUser");
        sessionUser.setNickname(user.getNickname());
        sessionUser.setAvatar(user.getAvatar());

        userService.updateUserInfo(sessionUser);

        session.setAttribute("activeUser",sessionUser);

        return "misc/success";
    }





}
