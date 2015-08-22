package newsfeed.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;

@Entity("messages")
@Indexes(@Index("messages, -date"))
public class Message {

    public Message() {
	likesCount = 0;
    }

    public Message(String text, User author) {
	this.text = text;
	this.author = author;
	date = new Date();
	liked = new HashSet<User>();
	likesCount = 0;
    }

    @Id
    private ObjectId id;

    @Property("text")
    private String text;

    @Reference("author")
    private User author;

    @Property("date")
    private Date date;

    @Reference("liked")
    private Set<User> liked;

    @Property("likes_count")
    private Integer likesCount;

    @Transient
    Boolean likedByCurrentUser;

    public ObjectId getId() {
	return id;
    }

    public void setId(ObjectId id) {
	this.id = id;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public User getAuthor() {
	return author;
    }

    public void setAuthor(User author) {
	this.author = author;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public Set<User> getLiked() {
	return liked;
    }

    public void setLiked(Set<User> liked) {
	this.liked = liked;
    }

    public Integer getLikesCount() {
	return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
	this.likesCount = likesCount;
    }

    public Boolean getLikedByCurrentUser() {
	return likedByCurrentUser;
    }

    public void setLikedByCurrentUser(Boolean likedByCurrentUser) {
	this.likedByCurrentUser = likedByCurrentUser;
    }
}
