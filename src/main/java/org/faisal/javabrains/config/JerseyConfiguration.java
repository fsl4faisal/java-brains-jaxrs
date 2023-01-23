package org.faisal.javabrains.config;

import org.faisal.javabrains.exceptions.DataNotFoundExceptionMapper;
import org.faisal.javabrains.exceptions.GenericExceptionMapper;
import org.faisal.javabrains.exceptions.ResourceNotFoundExceptionMapper;
import org.faisal.javabrains.resources.CommentResource;
import org.faisal.javabrains.resources.InjectResource;
import org.faisal.javabrains.resources.MessagesResource;
import org.faisal.javabrains.resources.ProfileResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        register(MessagesResource.class);
        register(CommentResource.class);
        register(InjectResource.class);
        register(ProfileResource.class);

        register(DataNotFoundExceptionMapper.class); //this worked BTW
        register(ResourceNotFoundExceptionMapper.class);
        register(GenericExceptionMapper.class);

    }
}
