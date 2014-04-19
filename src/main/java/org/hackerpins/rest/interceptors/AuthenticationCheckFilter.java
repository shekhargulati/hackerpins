package org.hackerpins.rest.interceptors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by shekhargulati on 18/04/14.
 */
@Provider
@LoggedIn
public class AuthenticationCheckFilter implements ContainerRequestFilter {

    @Context
    private HttpServletRequest request;
    @Inject
    private Logger logger;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        logger.info("In AuthenticationCheckFilter ...");
        HttpSession session = request.getSession(false);
        logger.info("Session " + session);
        if (session != null) {
            System.out.println(session.getAttribute("loggedInUser"));
            System.out.println(session.getId());
        }
        if (session == null || session.getAttribute("loggedInUser") == null) {
            logger.info("Returing Forbidden...");
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
    }
}
