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
    private static NoteService noteService = new NoteService();
    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {

        return Response.ok().build();
    }

    @GET
    @Produces("application/json")
    public ArrayList<Note> getNote(@PathParam("id") String id){
        ArrayList<Note> noteList = new ArrayList();
        noteList = noteService.getNote(id);

        return noteList;

    }

}
