package newsfeed.controller;

import java.util.List;

import newsfeed.model.User;
import newsfeed.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String newProfile(Model model) {
	List<User> userList = userService.getUsersSortedByRating();

	model.addAttribute("userList", userList);
	model.addAttribute("currentUser", SecurityContextHolder.getContext()
		.getAuthentication().getPrincipal());

	return "rating";
    }
}
