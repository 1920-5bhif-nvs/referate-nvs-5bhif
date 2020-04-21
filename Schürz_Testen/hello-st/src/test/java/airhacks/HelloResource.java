package airhacks;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "http://localhost:8080")
public interface HelloResource {

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    String content();

}
