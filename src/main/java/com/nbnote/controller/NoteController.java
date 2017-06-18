package com.nbnote.controller;

import com.nbnote.model.Note;
import com.nbnote.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by K on 2017. 6. 14..
 */

@Path("/note")
public class NoteController extends BaseController{
    private static Logger LOG = LoggerFactory.getLogger(NoteController.class);
    private  NoteService noteService = new NoteService();

    @PUT
    @Consumes("application/json")
    public Response writeNote(Note note){
        noteService.writeNode(note);
        return Response.ok().build();
    }

    @GET
    @Path("/{param}")
    @Produces("application/json")
    public ArrayList<Note> getNote(@PathParam("param") String id){
        ArrayList<Note> noteList;
        noteList = noteService.getAllNote(id);

        return noteList;

    }

}
