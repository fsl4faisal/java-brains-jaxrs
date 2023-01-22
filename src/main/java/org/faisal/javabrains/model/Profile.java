package org.faisal.javabrains.model;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.Instant;
import java.util.Date;

@XmlRootElement
public record Profile(long id, String profileName, String firstName, String lastName, Date created) {
    public Profile(long id, String profileName, String firstName, String lastName) {
        this(id, profileName, firstName, lastName, Date.from(Instant.now()));
    }
}
