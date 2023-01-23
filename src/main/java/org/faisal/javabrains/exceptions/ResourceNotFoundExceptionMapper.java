package org.faisal.javabrains.exceptions;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.faisal.javabrains.model.ErrorMessage;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

public class ResourceNotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        var errorMessage = new ErrorMessage(e.getMessage(), NOT_FOUND.getStatusCode(), "See documentation ResourceNotFoundExceptionMapper");
        return Response.status(NOT_FOUND).entity(errorMessage).build();
    }
}
