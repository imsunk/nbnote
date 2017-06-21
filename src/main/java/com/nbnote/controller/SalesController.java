package com.nbnote.controller;

import com.nbnote.model.Note;
import com.nbnote.service.NoteService;
import com.nbnote.service.SalesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * Created by K on 2017. 6. 21..
 */
public class SalesController {
    private static Logger LOG = LoggerFactory.getLogger(NoteController.class);
    private SalesService salesService = new SalesService();

    @PUT
    @Consumes("application/json")
    public Response writeNote(Note note){
        //salesService.writeNode(note);
        return Response.ok().build();
    }

}
