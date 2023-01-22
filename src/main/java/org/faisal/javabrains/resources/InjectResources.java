package org.faisal.javabrains.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import static jakarta.ws.rs.core.MediaType.TEXT_HTML;

@Path("/injectdemo")
@Consumes(TEXT_HTML)
@Produces(TEXT_HTML)
public class InjectResources {

    @GET
    @Path("annotations")
    public String getMatrixParamUsingAnnotations(
            @MatrixParam("param") String matrixParam,
            @HeaderParam("CustomHeaderParam") String customHeaderParam,
            @CookieParam("Cookie_2") String cookie) {
        return String.format("matrixParam:%s headerValue:%s cookie:%s",
                matrixParam,
                customHeaderParam,
                cookie);
    }

    @GET
    @Path("context")
    public String getParamsUsingContext(
            @Context UriInfo uriInfo,
            @Context HttpHeaders httpHeaders) {
        var getAllPathParams = uriInfo.getPathParameters();
        var getAllQueryParams = uriInfo.getQueryParameters();

        var cookies = httpHeaders.getCookies();

        return "test";
    }
}
