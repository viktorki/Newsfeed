package newsfeed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login")
    public String login() {
	return "login";
    }

    @RequestMapping(value = "/loginFailed")
    public String loginFailed(Model model) {
	model.addAttribute("error", messageSource.getMessage(
		"error.wrong.username.or.password", null, null));

	return "login";
    }
}
