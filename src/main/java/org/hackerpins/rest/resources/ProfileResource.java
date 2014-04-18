package org.hackerpins.rest.resources;

import org.hackerpins.business.domain.Profile;
import org.hackerpins.business.services.ProfileService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by shekhargulati on 18/04/14.
 */
@Path("/profile")
public class ProfileResource {

    @Inject
    private ProfileService profileService;

    @POST
    @Path("/register")
    public Response register(@Valid Profile profile){
        profileService.save(profile);
        Profile registerdProfile = profileService.findByEmail(profile.getEmail());
        return Response.status(Response.Status.CREATED).entity(registerdProfile).build();
    }
}
