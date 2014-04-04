package org.hackerpins.rest.resources;

import org.hackerpins.business.domain.Story;
import org.hackerpins.business.services.StoryService;
import org.hackerpins.rest.utils.Constants;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by shekhargulati on 04/04/14.
 */
@Path("stories")
public class StoryResource {

    @Inject
    private StoryService storyService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response submitStory(@NotNull @Valid Story story) {
        Story submittedStory = storyService.save(story);
        return Response.status(Response.Status.CREATED).entity(submittedStory).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Story findStory(@PathParam("id") Long id) {
        return storyService.findOne(id);
    }


    @GET
    @Produces("application/json")
    public List<Story> allStories(@Min(Constants.START_FOR_QUERY) @QueryParam("start") int start, @Max(Constants.MAX_RESULTS_FOR_QUERY) @QueryParam("max") int max) {
        return storyService.findAll(start, max);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateStory(@NotNull @Valid Story story){
        Story updatedStory = storyService.update(story);
        return Response.status(Response.Status.OK).entity(updatedStory).build();
    }
}
