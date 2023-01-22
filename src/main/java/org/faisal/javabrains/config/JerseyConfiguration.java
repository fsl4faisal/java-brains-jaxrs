package org.faisal.javabrains.config;

import org.faisal.javabrains.resources.InjectResources;
import org.faisal.javabrains.resources.MessagesResources;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(MessagesResources.class);
        register(InjectResources.class);
    }
}
