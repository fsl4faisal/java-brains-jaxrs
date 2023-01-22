package org.faisal.javabrains.resources;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageFilterBean {

    @QueryParam("year")
    private int year;
    @QueryParam("start")
    private int start;
    @QueryParam("size")
    private int size;
}