package org.faisal.javabrains.service;

import org.faisal.javabrains.config.DatabaseClass;
import org.faisal.javabrains.exceptions.DataNotFoundException;
import org.faisal.javabrains.model.Comment;
import org.faisal.javabrains.model.Message;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
//@Component
public class CommentService {
    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllCommentsOld(long messageId) {
        var message = messages.get(messageId);
        if (message != null) {
            return new ArrayList<>(message.getComments().values());
        } else throw new DataNotFoundException(String.format("MessageID: %d not found", messageId));
    }

    public List<Comment> getAllComments(long messageId) {
        return messages.entrySet().stream()
                .filter(entry -> entry.getValue().getId() == messageId)
                .findAny()
                .map(entry -> entry.getValue().getComments().values().stream().toList())
                .orElseThrow(() -> new DataNotFoundException(String.format("MessageID: %d not found", messageId)));
    }


//    public Comment getComment(long messageId, long commentId) {
//        ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "http://www.google.com");
//        Response response = Response.status(NOT_FOUND).entity(errorMessage).build();
//        Message message = messages.get(messageId);
//        if (message == null) {
//            throw new WebApplicationException(response);
//        }
//        Map<Long, Comment> comments = messages.get(messageId).getComments();
//        Comment comment = comments.get(commentId);
//        if (comment == null) {
//            throw new NotFoundException(response);
//        }
//        return comment;
//    }

    public Comment addComent(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size() + 1);
        comment.setCreated(Date.from(Instant.now()));
        comments.put(comment.getId(), comment);

        return comment;
    }

    public Comment updateComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }
}
