package newsfeed.service;

import java.io.IOException;
import java.util.List;

import newsfeed.dao.UserDAO;
import newsfeed.model.User;
import newsfeed.dto.UserDTO;

import org.springframework.security.crypto.codec.Base64;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MessageSource messageSource;

    public User getCurrentUser() {
	return (User) SecurityContextHolder.getContext().getAuthentication()
		.getPrincipal();
    }

    public User getUserById(ObjectId id) {
	return userDAO.get(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	User user = userDAO.getUserByUsername(username);

	if (user == null) {
	    throw new UsernameNotFoundException(messageSource.getMessage(
		    "error.no.username.found", null, null));
	}

	return user;
    }

    public User getUserByUsername(String username) {
	return userDAO.getUserByUsername(username);
    }

    public User saveUser(UserDTO userDTO) {
	User user;

	if (userDTO.getId() == null) {
	    user = new User();
	} else {
	    user = userDAO.get(userDTO.getId());
	}

	user.setUsername(userDTO.getUsername());
	user.setPassword(userDTO.getPassword());
	user.setFirstName(userDTO.getFirstName());
	user.setLastName(userDTO.getLastName());

	if (!userDTO.getAvatarFile().isEmpty()) {
	    try {
		userDTO.setAvatar(new String(Base64.encode(userDTO
			.getAvatarFile().getBytes())));
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	    user.setAvatar(userDTO.getAvatar());
	}

	userDAO.save(user);

	return user;
    }

    public List<User> getUsersSortedByRating() {
	return userDAO.getUsersSortedByRating();
    }

    public void banUser(User bannedUser) {
	User currentUser = (User) SecurityContextHolder.getContext()
		.getAuthentication().getPrincipal();

	increaseBans(bannedUser);
	currentUser.getBannedUserList().add(bannedUser);

	userDAO.save(currentUser);
    }

    public void increaseLikes(User user) {
	user.setLikesCount(user.getLikesCount() + 1);
	user.setRating(Double.valueOf(user.getLikesCount())
		/ (user.getBansCount() * user.getBansCount()));

	userDAO.save(user);
    }

    public void increaseBans(User user) {
	user.setBansCount(user.getBansCount() + 1);
	user.setRating(Double.valueOf(user.getLikesCount())
		/ (user.getBansCount() * user.getBansCount()));

	userDAO.save(user);
    }
}
