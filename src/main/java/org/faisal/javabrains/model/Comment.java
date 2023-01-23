package org.faisal.javabrains.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private long id;
    private String message;
    private Date created;
    private String author;

    public Comment(long id, String message, String author) {
        this(id, message, Date.from(Instant.now()), author);
    }
}
