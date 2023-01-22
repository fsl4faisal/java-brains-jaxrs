package org.faisal.javabrains.model;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.Instant;
import java.util.Date;

@XmlRootElement
public record Message(long id, String message, Date created, String author) {
    public Message(long id, String message, String author) {
        this(id, message, Date.from(Instant.now()), author);
    }
}

