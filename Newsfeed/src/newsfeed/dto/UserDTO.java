package newsfeed.dto;

import newsfeed.model.User;

import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

public class UserDTO {

    private ObjectId id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private MultipartFile avatarFile;

    private String avatar;

    public UserDTO() {
    }

    public UserDTO(User user) {
	id = user.getId();
	username = user.getUsername();
	password = user.getPassword();
	firstName = user.getFirstName();
	lastName = user.getLastName();
	avatar = user.getAvatar();
    }

    public ObjectId getId() {
	return id;
    }

    public void setId(ObjectId id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public MultipartFile getAvatarFile() {
	return avatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
	this.avatarFile = avatarFile;
    }

    public String getAvatar() {
	return avatar;
    }

    public void setAvatar(String avatar) {
	this.avatar = avatar;
    }
}
