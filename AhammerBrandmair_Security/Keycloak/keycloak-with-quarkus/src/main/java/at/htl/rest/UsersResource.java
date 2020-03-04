package at.htl.rest;

import at.htl.model.User;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//https://stackoverflow.com/a/30580796/3492994
// Resource VS Endpoint
// A resource is something REST specific
@Path("/api/users")
public class UsersResource {

    // Represents the currently logged in user
    @Inject
    SecurityIdentity identity;

    @GET
    @Path("/greeting")
    @Produces(MediaType.TEXT_PLAIN)
    public String greeting() {
        return Math.random() < 0.5? "Hello" : "Hi";
    }

    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    // Disable Browser Cache by sending a Cache-Control header
    @NoCache
    public User me() {
        return new User(identity);
    }
}
