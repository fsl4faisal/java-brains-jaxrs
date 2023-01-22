package org.faisal.javabrains.service;

import org.faisal.javabrains.config.DatabaseClass;
import org.faisal.javabrains.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
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
        return messages.get(messageId);
    }

    public Message addMessage(Message message) {
        messages.put(message.id(),
                new Message(messages.size() + 1,
                        message.message(),
                        message.created(),
                        message.author()));
        return messages.get(message.id());
    }

    public Message updateMessage(Message message) {
        if (message.id() <= 0)
            return null;
        messages.put(message.id(), message);
        return message;
    }

    public Message removeMessage(Message message) {
        return messages.remove(message.id());
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
            calender.setTime(entry.getValue().created());
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
