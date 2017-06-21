package com.nbnote.controller;

import com.nbnote.model.Note;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by K on 2017. 6. 18..
 */
public class NoteControllerTest extends JerseyTest{
    @Override
    protected Application configure() {
        return  new ResourceConfig(NoteController.class);
    }

    @Test
    public void NoteListTest(){
        Response response = target("/note/lskim").request().get();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void writeNoteTest(){
        Date date = new Date();
        Note note = new Note(1,"test","laesunk",date,"sunny","21c","에덴농장","테스트입니다","무3개","3000","비료","20000");
        Entity<Note> noteEntity = Entity.entity(note, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/note").request().put(noteEntity);
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.getStatus());

    }

    @Test
    public void modNoteTest(){
        Date date = new Date();
        Note note = new Note(1,"test","laesunk",date,"sunny","21c","에덴농장","테스트입니다","무3개","3000","비료","20000");
        Entity<Note> noteEntity = Entity.entity(note, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/note/1").request().put(noteEntity);
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.getStatus());
    }

    /*
    @Test
    public void deleteNoteTest() {
        Response response = target("/note/1").request().delete();
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.getStatus());
    }
    */

}
