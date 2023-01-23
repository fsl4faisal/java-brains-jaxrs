package org.faisal.javabrains.service;

import org.faisal.javabrains.config.DatabaseClass;
import org.faisal.javabrains.exceptions.DataNotFoundException;
import org.faisal.javabrains.model.Message;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.*;
import java.util.function.Predicate;

import static java.util.Calendar.YEAR;

@Service
public class MessageService {
    private final Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {
        messages.put(1L, new Message(1, "Hello World", "Faisal"));
        messages.put(2L, new Message(2, "Hello Jersey", "Md"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());

    }

    public Message getMessage(long messageId) {
        var message = messages.get(messageId);
        if (message != null) return message;
        else throw new DataNotFoundException(messageId + " Not Found");
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        message.setCreated(Date.from(Instant.now()));
        message.setComments(new HashMap<>());
        messages.put(message.getId(), message);
        return messages.get(message.getId());
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0)
            return null;
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(Message message) {
        return messages.remove(message.getId());
    }

    public List<Message> getAllMessagesForYear(int year) {
        var calender = Calendar.getInstance();
        return messages.entrySet().stream()
                .filter(byYear(year, calender))
                .map(entry -> entry.getValue())
                .toList();
    }

    private Predicate<Map.Entry<Long, Message>> byYear(int year, Calendar calender) {
        return entry -> {
            calender.setTime(entry.getValue().getCreated());
            if (calender.get(YEAR) == year) {
                return true;
            } else {
                return false;
            }
        };
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        ArrayList<Message> list = new ArrayList<Message>(messages.values());
        if (start + size > list.size()) return new ArrayList<Message>();
        return list.subList(start, start + size);
    }
}
