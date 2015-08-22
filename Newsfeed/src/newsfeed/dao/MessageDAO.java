package newsfeed.dao;

import java.util.List;

import newsfeed.model.Message;
import newsfeed.model.User;
import newsfeed.util.Constants;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAO extends BasicDAO<Message, ObjectId> {

    @Autowired
    public MessageDAO(Datastore ds) {
	super(ds);
    }

    public List<Message> getRecentMessages(User user, Long page) {
	Query<Message> q = createQuery();

	if (!user.getBannedUserList().isEmpty()) {
	    q.field("author").notIn(user.getBannedUserList());
	}

	q.order("-date");
	q.offset((int) (page * Constants.MESSAGES_PER_PAGE));
	q.limit(Constants.MESSAGES_PER_PAGE + 1);

	return q.asList();
    }
}
