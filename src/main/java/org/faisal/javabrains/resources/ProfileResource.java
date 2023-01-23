package org.faisal.javabrains.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.faisal.javabrains.model.Profile;
import org.faisal.javabrains.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.OK;

@Path("/profiles")
@Controller
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ProfileResource {
    @Autowired
    private ProfileService profileService;

    @GET
    public Response getAllProfiles() {
        var profiles = profileService.getAllProfiles();
        return buildResponse(profiles, OK);
    }

    @POST
    public Response createProfile(Profile profile) {
        profileService.createProfile(profile);
        return buildResponse(profile, CREATED);
    }

    @GET
    @Path("/{id}")
    public Response getProfile(@PathParam("id") long id){
        return buildResponse(profileService.getProfile(id),OK);
    }

    @PUT
    @Path("/{id}")
    public Response updateProfile(Profile profile) {
        var updatedProfile = profileService.updateProfile(profile);
        return buildResponse(updatedProfile, OK);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProfile(@PathParam("id") long id) {
        profileService.deleteProfile(id);
        return buildResponse(OK);
    }

    private static Response buildResponse(Object profiles, Response.Status status) {
        return Response.status(status)
                .entity(profiles)
                .build();
    }

    private static Response buildResponse(Response.Status status) {
        return Response.status(status)
                .build();
    }
}
