package ssm.controller;

import ssm.exception.CustomException;
import ssm.model.User;
import ssm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signinPage(HttpServletRequest request, HttpSession session) {
        //保存上个页面的url
        if (session.getAttribute("previousPage")==null){

            session.setAttribute("previousPage", request.getHeader("Referer"));
        }
        return "signin";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupPage(HttpServletRequest request, HttpSession session) {
        //保存上个页面的url
        if (session.getAttribute("previousPage")==null){

            session.setAttribute("previousPage", request.getHeader("Referer"));
        }
        return "signup";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(Model model, HttpSession session,
                         String username, String password, boolean rememberme) {
        User user = null;
        try {
            user = accountService.signin(username, password);
        } catch (CustomException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "signin";
        }

        if (user != null) {
            session.setAttribute("activeUser", user);
            String url = (String) session.getAttribute("previousPage");

            return "redirect:" + ((url != null)?url : "/home");
        }
        return "signin";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(Model model, HttpSession session,
                         String username, String password) {
        try {
            accountService.checkNamePass(username,password);
        } catch (CustomException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "signup";
        }

        User user = accountService.signup(username,password);

        session.setAttribute("activeUser", user);
        String url = (String) session.getAttribute("previousPage");
        return "redirect:" + ((url != null)?url : "/home");
    }

    @RequestMapping("/signout")
    public String signoff(HttpServletRequest request,HttpSession session){
        session.setAttribute("previousPage",null);
        session.setAttribute("activeUser", null);
        return "redirect:"  + request.getHeader("Referer");

    }

}
