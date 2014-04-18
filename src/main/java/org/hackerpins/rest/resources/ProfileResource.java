package org.hackerpins.rest.resources;

import org.hackerpins.business.domain.Profile;
import org.hackerpins.business.services.ProfileService;
import org.hackerpins.rest.interceptors.Login;
import org.hackerpins.rest.vo.Credentials;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by shekhargulati on 18/04/14.
 */
@Path("/profiles")
public class ProfileResource {

    @Inject
    private ProfileService profileService;

    @POST
    @Path("/register")
    @Produces("application/json")
    @Consumes("application/json")
    public Response register(@Valid Profile profile) {
        profileService.save(profile);
        Profile registerdProfile = profileService.findByEmail(profile.getEmail());
        return Response.status(Response.Status.CREATED).entity(registerdProfile).build();
    }

    @POST
    @Path("/login")
    @Produces("application/json")
    @Login
    public Response login(@Valid Credentials credentials) {
        Profile profile = profileService.findByUsernameOrEmailAndPassword(credentials.getUsername(), credentials.getPassword());
        if (profile == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Username/password combination incorrect").build();
        }
        return Response.status(Response.Status.OK).entity(profile).build();
    }
}
