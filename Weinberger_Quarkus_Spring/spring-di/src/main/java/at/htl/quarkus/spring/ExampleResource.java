package at.htl.quarkus.spring;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greeting")
public class ExampleResource {

    @Autowired
    GreeterBean greeterBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greeterBean.greet("und Prof. Stütz");
    }
}