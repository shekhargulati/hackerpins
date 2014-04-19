package org.hackerpins.rest.interceptors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by shekhargulati on 18/04/14.
 */
@Login
@Provider
public class LoginResponseFilter implements ContainerResponseFilter {

    @Context
    private HttpServletRequest request;
    @Inject
    private Logger logger;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        logger.info("Inside LoginResponseFilter...");
        int status = responseContext.getStatus();
        if (status == Response.Status.OK.getStatusCode()) {
            logger.info("User is successfully loggedin..");
            HttpSession session = request.getSession(true);
            System.out.println("LoginResponseFilter sessionId : " + session.getId());
            session.setAttribute("loggedInUser", responseContext.getEntity());
        }
    }
}
