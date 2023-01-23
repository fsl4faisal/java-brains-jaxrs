package org.faisal.javabrains.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.Getter;
import lombok.Setter;
import org.faisal.javabrains.model.Message;
import org.faisal.javabrains.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URISyntaxException;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/messages")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Controller
public class MessagesResource {
    @Autowired
    private MessageService messageService;

    @Autowired
    private CommentResource commentResource;

    @GET
    public List<Message> getMessagesOld(@QueryParam("year") int year,
                                        @QueryParam("start") int start,
                                        @QueryParam("size") int size) {
        if (year > 0) return messageService.getAllMessagesForYear(year);
        if (start >= 0 && size > 0) return messageService.getAllMessagesPaginated(start, size);
        return messageService.getAllMessages();
    }

    @GET
    @Path("/v2")
    public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
        if (messageFilterBean.getYear() > 0) return messageService.getAllMessagesForYear(messageFilterBean.getYear());
        if (messageFilterBean.getStart() >= 0 && messageFilterBean.getSize() > 0)
            return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
        return messageService.getAllMessages();
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
        var newMessage = messageService.addMessage(message, uriInfo.getAbsolutePath());
        var newUri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(newMessage.getId()))
                .build();
        return Response
                .created(newUri)
                .entity(newMessage)
                .build();
    }

    @GET
    @Path("/{id}")
    public Message getMessage(@PathParam("id") long id) {
        return messageService.getMessage(id);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResources() {
        return commentResource;
    }
}

@Getter
@Setter
class MessageFilterBean {
    @QueryParam("year")
    private int year;
    @QueryParam("start")
    private int start;
    @QueryParam("size")
    private int size;
}


