package org.faisal.javabrains.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.faisal.javabrains.model.ErrorMessage;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

//@Provider--->BUT THIS DIDN'T WORK Registers this in JAXRS same as the register(DataNotFoundExceptionMapper.class) in JerseyConfiguration
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
    @Override
    public Response toResponse(DataNotFoundException e) {
        //var exception = new DataNotFoundException(e.getMessage());//we will not send this instead we will send the ErrorResponse that we built
        var errorResponse = new ErrorMessage(
                e.getMessage(),
                NOT_FOUND.getStatusCode(),
                "Lookup the documentation here ...www.google.com DataNotFoundExceptionMapper");

        return Response.status(NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}
