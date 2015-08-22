package newsfeed.controller;

import javax.servlet.http.HttpSession;

import newsfeed.model.User;
import newsfeed.dto.UserDTO;
import newsfeed.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProfile(Model model) {
	UserDTO userDTO = new UserDTO();

	model.addAttribute("user", userDTO);

	return "registration";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createProfile(@ModelAttribute("user") UserDTO userDTO,
	    RedirectAttributes redirectAttributes) {
	userService.saveUser(userDTO);
	redirectAttributes.addFlashAttribute("registered", true);

	return "redirect:/login";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProfile(Model model, HttpSession session) {
	User user = (User) session.getAttribute("currentUser");
	UserDTO userDTO = new UserDTO(user);

	model.addAttribute("user", userDTO);

	return "editProfile";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveProfile(@ModelAttribute("user") UserDTO userDTO,
	    Model model, HttpSession session) {
	User savedUser = userService.saveUser(userDTO);
	session.setAttribute("currentUser", savedUser);
	model.addAttribute("user", userDTO);
	model.addAttribute("success", true);

	return "editProfile";
    }
}
