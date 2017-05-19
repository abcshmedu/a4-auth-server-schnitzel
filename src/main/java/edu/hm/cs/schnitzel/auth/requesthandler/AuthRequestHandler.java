/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.requesthandler;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import edu.hm.cs.schnitzel.auth.result.Result;
import edu.hm.cs.schnitzel.auth.services.AuthService;
import edu.hm.cs.schnitzel.auth.services.Service;

/**
 *
 * @author konopac
 */
@Path("/auth")
public class AuthRequestHandler {

	// Fields

	/**
	 * The service object (AuthService)
	 */
	private final Service service = new AuthService();

	// Public Methods

    /**
     * Validate token with authentification server.
     * 
     * Accepts token as JSON
     * 
     * @param token is the token to be validated
     * @return the response with code, message and content
     */
    @POST
    @Path("/token")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateToken(final String token) {
    	// Call service method and wait for result
    	final Result result = getService().validateToken(token);
    	// Build JSON-Content
    	final JSONObject content = new JSONObject()
    			.put("valid", 	result.getContent().isValid())
    			.put("role", 	result.getContent().getUserRole());
    	// Build JSON-Result
    	final JSONObject json = new JSONObject()
		    	.put("code",	result.getCode())
		    	.put("message",	result.getMessage())
		    	.put("content", content);
		return Response.status(Response.Status.ACCEPTED).entity(json).build();
    }

    /**
     * Request token from authentification server.
     * 
     * Accepts username and password as JSON
     * 
     * @param username is the name of the user
     * @param password is the password of the user
     * 
     * @return the response with code, message and content
     */
    @POST
    @Path("")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response requestToken(final String username, final String password) {
    	// Call service method and wait for result
    	final Result result = getService().requestToken(username, password);
    	// Build JSON-Content
    	final JSONObject content = new JSONObject()
    			.put("token", 	result.getContent().getToken())
    			.put("role", 	result.getContent().getUserRole());
    	// Build JSON-Result
    	final JSONObject json = new JSONObject()
		    	.put("code",	result.getCode())
		    	.put("message",	result.getMessage())
		    	.put("content", content);
		return Response.status(Response.Status.ACCEPTED).entity(json).build();
    }

    // Getter and Setter

    /**
     * Getter for service.
     * 
     * @return the service object
     */
    private Service getService() {
		return service;
	}
}
