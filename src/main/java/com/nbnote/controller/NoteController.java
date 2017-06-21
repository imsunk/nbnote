package com.nbnote.controller;

import com.nbnote.model.Note;
import com.nbnote.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by K on 2017. 6. 14..
 */

@Path("/note")
public class NoteController extends BaseController{
    private static Logger LOG = LoggerFactory.getLogger(NoteController.class);
    private  NoteService noteService = new NoteService();

    @POST
    @Consumes("application/json")
    public Response writeNote(Note note){
        noteService.writeNode(note);
        return Response.ok().build();
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Note> getNote(@PathParam("userId")String userId){
        ArrayList<Note> noteList;
        noteList = noteService.getAllNote(userId);

        return noteList;
    }

    @PUT
    @Path("{noteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modNote(Note note, @PathParam("noteId")int id) {
        noteService.modNote(note,id);
        return Response.ok().build();
    }

    @DELETE
    @Path("{noteId}")
    public Response deleteNote(@PathParam("noteId")int noteId){
        noteService.deleteNote(noteId);

        return Response.ok().build();
    }


}
