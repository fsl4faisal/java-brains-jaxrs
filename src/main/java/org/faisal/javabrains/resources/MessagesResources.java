package org.faisal.javabrains.resources;

import jakarta.ws.rs.*;
import org.faisal.javabrains.model.Message;
import org.faisal.javabrains.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/messages")
@Controller
public class MessagesResources {
    @Autowired
    private MessageService messageService;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Message> getMessages(@QueryParam("year") int year,
                                     @QueryParam("start") int start,
                                     @QueryParam("size") int size) {
        if (year > 0) return messageService.getAllMessagesForYear(year);
        if (start >= 0 && size > 0) return messageService.getAllMessagesPaginated(start, size);
        return messageService.getAllMessages();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/v2")
    public List<Message> getMessagesv2(@BeanParam MessageFilterBean messageFilterBean) {
        if (messageFilterBean.getYear() > 0) return messageService.getAllMessagesForYear(messageFilterBean.getYear());
        if (messageFilterBean.getStart() >= 0 && messageFilterBean.getSize() > 0)
            return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
        return messageService.getAllMessages();
    }
}


