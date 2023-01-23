package org.faisal.javabrains.config;

import org.faisal.javabrains.model.Message;
import org.faisal.javabrains.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {
    private static final Map<Long, Message> messages = new HashMap<>();
    private static final Map<Long, Profile> profiles = new HashMap<>();
    public static Map<Long, Message> getMessages() {
        return messages;
    }
    public static Map<Long, Profile> getProfiles() {
        return profiles;
    }
}
