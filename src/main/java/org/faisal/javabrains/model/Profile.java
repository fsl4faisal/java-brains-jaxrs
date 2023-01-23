package org.faisal.javabrains.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    private long id;
    private String profileName;
    private String firstName;
    private String lastName;
    private Date created;


    public Profile(long id, String profileName, String firstName, String lastName) {
        this(id, profileName, firstName, lastName, Date.from(Instant.now()));
    }
}