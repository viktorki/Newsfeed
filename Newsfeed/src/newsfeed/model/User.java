package newsfeed.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity("users")
@Indexes(@Index("users, -rating"))
public class User implements UserDetails {

    private static final long serialVersionUID = 4444637454856129017L;

    @Id
    private ObjectId id;

    @Property("username")
    private String username;

    @Property("password")
    private String password;

    @Property("first_name")
    private String firstName;

    @Property("last_name")
    private String lastName;

    @Property("avatar")
    private String avatar;

    @Reference("banned_users")
    private List<User> bannedUserList;

    @Property("likes_count")
    private Integer likesCount;

    @Property("bans_count")
    private Integer bansCount;

    @Property("rating")
    private Double rating;

    @Transient
    List<GrantedAuthority> authorities;

    public User() {
	likesCount = 0;
	bansCount = 0;
	bannedUserList = new ArrayList<User>();
	authorities = new ArrayList<GrantedAuthority>();
	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public ObjectId getId() {
	return id;
    }

    public void setId(ObjectId id) {
	this.id = id;
    }

    @Override
    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    @Override
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

    public String getAvatar() {
	return avatar;
    }

    public void setAvatar(String avatar) {
	this.avatar = avatar;
    }

    public List<User> getBannedUserList() {
	return bannedUserList;
    }

    public void setBannedUserList(List<User> bannedUserList) {
	this.bannedUserList = bannedUserList;
    }

    public Integer getLikesCount() {
	return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
	this.likesCount = likesCount;
    }

    public Integer getBansCount() {
	return bansCount;
    }

    public void setBansCount(Integer bansCount) {
	this.bansCount = bansCount;
    }

    public Double getRating() {
	return rating;
    }

    public void setRating(Double rating) {
	this.rating = rating;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
	return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }
}
