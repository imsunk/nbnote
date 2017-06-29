package com.nbnote.controller;

import com.nbnote.model.Comment;
import com.nbnote.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by K on 2017. 6. 26..
 */
@Path("/comment")
public class CommentController {
    private static Logger LOG = LoggerFactory.getLogger(NoteController.class);
    private CommentService commentService = new CommentService();
    @GET
    @Path("{userId}/{noteId}")
    public ArrayList<Comment> getComments(@PathParam("userId")String userId, @PathParam("noteId")int noteId){
        ArrayList<Comment> comments = commentService.getAllReply(userId,noteId);
        return comments;
    }
    /*
    @PUT
    @Path("{userId}/{noteId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response writeComment(Comment comment){
        commentService.writeComment(comment);
        return Response.ok().build();
    } */

}
