package org.hackerpins.interceptors;

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
public class LoginRequestFilter implements ContainerRequestFilter {

    @Context
    private HttpServletRequest request;
    @Inject
    private Logger logger;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        logger.info("In LoginRequestFilter ...");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            logger.info("Returing Forbidden...");
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
    }
}
