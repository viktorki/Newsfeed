package newsfeed.service;

import java.util.List;

import newsfeed.dao.MessageDAO;
import newsfeed.model.Message;
import newsfeed.model.User;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageDAO messageDAO;

    public Message getMessageById(ObjectId id) {
	return messageDAO.get(id);
    }

    public List<Message> getRecentMessages(User user, Long page) {
	return messageDAO.getRecentMessages(user, page);
    }

    public void saveMessage(Message message) {
	messageDAO.save(message);
    }

    public void likeMessage(Message message) {
	User currentUser = (User) SecurityContextHolder.getContext()
		.getAuthentication().getPrincipal();

	message.getLiked().add(currentUser);
	message.setLikesCount(message.getLikesCount() + 1);

	messageDAO.save(message);
    }
}
