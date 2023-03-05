package de.mcmics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/todos")
public interface ToDoService {

    @GET
    @Path("/{index}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    ToDo getAt(@PathParam("index") int index);

    @GET
    @Path("/{index}")
    @Produces({MediaType.TEXT_PLAIN})
    String getAtAsString(@PathParam("index") int index);

}
