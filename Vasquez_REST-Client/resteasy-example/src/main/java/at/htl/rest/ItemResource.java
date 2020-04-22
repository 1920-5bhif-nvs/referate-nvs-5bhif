package at.htl.rest;

import at.htl.model.Item;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {

    private Set<Item> items = Collections.synchronizedSet(new HashSet<>());

    public ItemResource(){
        items.add(new Item("Milk", 1.0));
        items.add(new Item("Bread", 0.5));
    }

    @GET
    public Response get(){
        return Response.ok(items).build();
    }

    @POST
    public Response post(Item item){
        if(!item.getName().isEmpty() && item.getPrice() != 0){
            items.add(item);
            return Response.ok(item).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
