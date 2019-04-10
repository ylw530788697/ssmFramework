package cn.evan.ssm.controller;

import cn.evan.ssm.model.User;
import cn.evan.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginAction {
    @Resource
    private UserService userService;

    @RequestMapping("/toLogin.do")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login.shtml")
    public void login(User user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        User userByUserNameAndPassword = userService.findUserByUserNameAndPassword(user);
        if(userByUserNameAndPassword==null){
            response.getWriter().write("0");
        }
        else {
            request.getSession().setAttribute("user",userByUserNameAndPassword);
            response.getWriter().write("1");
        }
    }
}
