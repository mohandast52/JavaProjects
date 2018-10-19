package org.mohan.messenger.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.mohan.messenger.model.ErrorMessage;

// Provider: telling JAX-RS that this is present
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		/*
		 * JAX RS will call this method and passes exception here! to catch exception,
		 * prepare response and send it back
		 */
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "www.google.com");
		return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).entity(errorMessage).build();
	}

}
