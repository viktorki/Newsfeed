package newsfeed.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import newsfeed.model.Message;
import newsfeed.model.User;
import newsfeed.service.MessageService;
import newsfeed.service.UserService;
import newsfeed.util.Constants;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    UserService userService;

    @RequestMapping("/list/{page}")
    public String showMessageList(Model model, @PathVariable("page") Long page,
	    HttpSession session) {
	User currentUser = (User) session.getAttribute("currentUser");
	List<Message> messageList = messageService.getRecentMessages(
		currentUser, page);
	Boolean moreMessages = messageList.size() == Constants.MESSAGES_PER_PAGE + 1;
	Integer messagesCount;
	List<Message> result = new ArrayList<Message>();

	if (moreMessages) {
	    messagesCount = Constants.MESSAGES_PER_PAGE;
	} else {
	    messagesCount = messageList.size();
	}

	for (int i = messagesCount - 1; i >= 0; i--) {
	    Message currentMessage = messageList.get(i);
	    Boolean likedByCurrentUser = false;

	    for (User userLiked : currentMessage.getLiked()) {
		if (userLiked.getId().equals(currentUser.getId())) {
		    likedByCurrentUser = true;
		    break;
		}
	    }

	    currentMessage.setLikedByCurrentUser(likedByCurrentUser);
	    result.add(currentMessage);
	}

	model.addAttribute("messageList", result);
	model.addAttribute("moreMessages", moreMessages);
	model.addAttribute("currentUser", currentUser);

	return "messageList";
    }

    @RequestMapping("/write")
    public void saveMessage(@RequestParam("text") String text) {
	User author = userService.getUserByUsername(SecurityContextHolder
		.getContext().getAuthentication().getName());
	Message message = new Message(text, author);

	messageService.saveMessage(message);
    }

    @RequestMapping("/like/{id}")
    public void likeMessage(@PathVariable("id") ObjectId id) {
	Message message = messageService.getMessageById(id);

	messageService.likeMessage(message);
	userService.increaseLikes(message.getAuthor());
    }

    @RequestMapping("/ban/{id}")
    public String banUser(@PathVariable("id") ObjectId id,
	    RedirectAttributes redirectAttributes) {
	User user = userService.getUserById(id);

	userService.banUser(user);
	redirectAttributes.addFlashAttribute("bannedUser", user.getFirstName()
		+ " " + user.getLastName());

	return "redirect:/";
    }
}
