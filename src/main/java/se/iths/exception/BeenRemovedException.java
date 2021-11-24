package se.iths.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BeenRemovedException extends WebApplicationException {
    public BeenRemovedException(String message) {
        super(Response.status(Response.Status.GONE)
                .entity(message).type(MediaType.APPLICATION_JSON_TYPE).build());
}
}