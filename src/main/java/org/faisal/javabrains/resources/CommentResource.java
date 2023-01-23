package org.faisal.javabrains.resources;

import jakarta.ws.rs.*;
import org.faisal.javabrains.model.Comment;
import org.faisal.javabrains.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/") //Because path will be inferred from the MessageResources since CommentResource is a subresource
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Controller
public class CommentResource {

    @Autowired
    CommentService commentService;

    @GET
    @Path("/{commentId}")
    public String getComment(@PathParam("messageId") int messageId, @PathParam("commentId") int commentId) {
        return "new sub resource commentId:" + commentId + " messageId:" + messageId;
    }


    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
        return commentService.getAllComments(messageId);
    }

//    @GET
//    @Path("/{commentId}")
//    public Comment getMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
//        return commentService.getComment(messageId, commentId);
//    }

    @POST
    public Comment addMessage(@PathParam("messageId") long messageId, Comment comment) {
        return commentService.addComent(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long id, Comment comment) {
        comment.setId(id);
        return commentService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        commentService.removeComment(messageId, commentId);
    }
}

