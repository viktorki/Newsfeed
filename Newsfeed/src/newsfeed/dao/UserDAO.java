package newsfeed.dao;

import java.util.List;

import newsfeed.model.User;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends BasicDAO<User, ObjectId> {

    @Autowired
    public UserDAO(Datastore ds) {
	super(ds);
    }

    public User getUserByUsername(String username) {
	Query<User> q = createQuery();

	q.field("username").equal(username);

	return find(q).get();
    }

    public List<User> getUsersSortedByRating() {
	Query<User> q = createQuery();

	q.order("-rating");

	return find(q).asList();
    }
}
