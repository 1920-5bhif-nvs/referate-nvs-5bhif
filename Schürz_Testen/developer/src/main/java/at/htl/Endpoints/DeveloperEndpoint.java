package at.htl.Endpoints;

import at.htl.dao.DeveloperDao;
import at.htl.model.Developer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/developer")
public class DeveloperEndpoint {
    @Inject
    DeveloperDao developerDao;

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Developer findDev(@PathParam("name") String name) {
        return developerDao.findByName(name);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDev(Developer developer){
        developer.persist();
        return  Response.created(
                URI.create("http://localhost:8080/developer/" + developer.id)).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDev(@PathParam("id") String id){
        try {
            System.out.println("id: "+ id);
            Developer developer = developerDao.findById(Long.parseLong(id));
            System.out.println("id dev: "+ developer.id);
            developerDao.delete(developer);
            return Response.ok().entity(developer).build();
        }catch (Exception ex){
            return Response.status(404).build();
        }

    }
}