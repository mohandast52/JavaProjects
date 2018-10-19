package org.mohan.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)

public class InjectDemoResource {
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
			@HeaderParam("customHeaderParam") String customHeaderParam) {
		/* MatrixParam: arguments seperated using semi-colon (;) */
		/* CookieParam: to access cookies */
		/* FormParam: to get data from forms like textbox */
		return "Matrix Param: " + matrixParam + "\nHeader Param: " + customHeaderParam;
	}

	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {

		/*
		 * Contest: if we don't know what to access and which name to use (i.e UriInfo),
		 * we can use @Context with UriInfo to access
		 */

		/*
		 * HttpHeaders provides with header access
		 */

		/* @BeanParam: one more way of access parameters passed */
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();
		return "Path: " + path + "\nCookies: " + cookies;
	}
}
