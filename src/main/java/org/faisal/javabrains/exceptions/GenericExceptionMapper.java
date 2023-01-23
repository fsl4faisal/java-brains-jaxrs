package org.faisal.javabrains.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.faisal.javabrains.model.ErrorMessage;

import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable e) {
        var errorMessage = new ErrorMessage(e.getMessage(), INTERNAL_SERVER_ERROR.getStatusCode(), "See documentation generic");
        return Response.status(INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }
}
