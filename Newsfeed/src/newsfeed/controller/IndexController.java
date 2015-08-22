package newsfeed.controller;

import javax.servlet.http.HttpSession;

import newsfeed.model.User;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
	User currentUser = (User) SecurityContextHolder.getContext()
		.getAuthentication().getPrincipal();

	session.setAttribute("currentUser", currentUser);

	return "index";
    }
}
