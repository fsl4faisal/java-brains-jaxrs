package org.faisal.javabrains.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.*;


@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private long id;
    private String message;
    private Date created;
    private String author;
    @XmlTransient
    private Map<Long, Comment> comments;
    private List<Link> links;

    public Message(long id, String message, String author) {
        this(id, message, Date.from(Instant.now()), author, new HashMap<>(), new ArrayList<>());
    }
}

