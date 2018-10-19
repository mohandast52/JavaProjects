package org.mohan.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.mohan.messenger.model.Comment;
import org.mohan.messenger.service.CommentService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CommentResource {
	CommentService commentService = new CommentService();

	@GET
	@Path("/")
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentService.getAllComments(messageId);
	}

	@POST
	@Path("/")
	public Response addComment(@Context UriInfo uriInfo, @PathParam("messageId") long messageId, Comment comment) {
		/*
		 * If the resource is created, we have STATUS code 201. But by default we send
		 * 200 code, so to change we need to use 'Response' Class
		 */
		// return commentService.addComment(messageId, comment);

		Comment newComment = commentService.addComment(messageId, comment);
		URI uri = uriInfo.getAbsolutePathBuilder().path(newComment.getId() + "").build();
		return Response.created(uri).entity(newComment).build();

		// call build at end and return it!
	}

	@PUT
	@Path("/{commentId}")
	public Comment updateMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId,
			Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}

	@DELETE
	@Path("/{commentId}")
	public void deleteMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		commentService.removeComment(messageId, commentId);
	}

	@GET
	@Path("/{commentId}")
	public Comment name(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.getComment(messageId, commentId);
	}
}
