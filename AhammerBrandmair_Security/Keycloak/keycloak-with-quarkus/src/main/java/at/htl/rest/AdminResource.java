package at.htl.rest;

import io.quarkus.security.Authenticated;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/admin")
@Authenticated // Keycloak takes care of the protection
public class AdminResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String admin() {
        return "granted";
    }

    @GET
    @Path("/api/nuke")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteEverything() {
        // TODO: Delete everything, including all backups.
        return "granted";
    }
}
