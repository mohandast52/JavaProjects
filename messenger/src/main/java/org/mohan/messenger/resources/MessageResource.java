package org.mohan.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.mohan.messenger.model.Message;
import org.mohan.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class MessageResource {

	MessageService messageService = new MessageService();

	/*
	 * for converting to JSON type we don't have something like JAXB which converts
	 * to XML, so we add 'moxy' to maven dependency!
	 */

	@GET
	// @Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start,
			@QueryParam("size") int size) {
		/*-
		 * QueryParam : is used to fetch the parameters passed in query 
		 * eg. /messages?year=2015 then year is passed as parameter,
		 * and if year is not passed (eg. /messages/) default value of int 
		 * i.e 0 is set as value
		 */
		if (year > 0) {
			return messageService.getAllMessagesForYear(year);
		}

		// start is zero bases
		if (start >= 0 && size > 0) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessage();
	}

	/*
	 * Consumes: Since consumes uses JSON type, Jersey will convert JSON to the
	 * parameter passed type, i.e. to Message Class
	 */

	@POST
	public Message addMessage(Message message) {
		/*
		 * everything is converted from JSON to Message class using JERSEY, except ID
		 * because ID will be set by program (i.e. everything in JSON to Class's
		 * property)
		 */

		/*-
		 * if we didn't set the JSON's name exactly in class name, that property won't
		 * be set 
		 * 
		 * EG) created is a property in class, and if we put 'creat', then
		 * created property won't be set and it will not be sent in response back
		 * 
		 */
		System.out.println(message.toString());
		return messageService.addMessage(message);
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId) {
		messageService.removeMessage(messageId);
	}

	/* {messageId} : denotes variable */

	@GET
	@Path("/{messageId}")
	public Message name(@PathParam("messageId") long messageId) {
		/*
		 * PathParam: use {messageId} as a variable and we are accessing it by denoting
		 * pathtparam in the arugment of the method
		 */

		return messageService.getMessage(messageId);
	}

	// for comments (SUBRESOURCES concept)
	/*
	 * no @GET or @POST is used because we want to delegate the request to
	 * CommentResource and also the remainder path is sent to CommentResource
	 */
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
