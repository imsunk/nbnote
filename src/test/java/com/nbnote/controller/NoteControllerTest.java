package com.nbnote.controller;

import com.nbnote.db.DbHandler;
import com.nbnote.model.Note;
import com.nbnote.model.User;
import com.nbnote.model.UserSecurity;
import com.nbnote.service.TokenDAO;
import com.nbnote.service.UserDAO;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by K on 2017. 6. 18..
 */
public class NoteControllerTest extends JerseyTest{
    private TokenDAO tokenDao = new TokenDAO();
    private UserDAO userDao = new UserDAO();


    @Test
    public void getToken() {
        Response response;
        MultivaluedMap<String, String> formData;
        formData = new MultivaluedHashMap<String, String>();
        formData.add("id", "laesunk");
        String id = "laesunk";
        response = target("/auth/").request().get();
        Assert.assertEquals(200, response.getStatus());
        UserSecurity userSec = response.readEntity(UserSecurity.class);
        Assert.assertNotNull(userSec);
        Assert.assertEquals(userSec.getId(),"laesunk");
    }

    @Override
    protected Application configure() {
        return  new ResourceConfig(NoteController.class);
    }

    @Test
    public void AllNoteListTest(){
        Response response = target("/note/users/laesunk").request().get();
        Assert.assertNotNull(response);
        System.out.println(response);
    }

    @Test
    public void getNoteTest(){
        Response response = target("/note/users/lskim/notes/3").request().get();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void writeNoteTest(){
        Date date = new Date();
        Note note = new Note(1,"test","laesunk",date,"sunny","21c","에덴농장","테스트입니다","무3개","3000","비료","20000");
        Entity<Note> noteEntity = Entity.entity(note, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/note").request().post(noteEntity);
        Assert.assertNotNull(response);

        Assert.assertEquals(200,response.getStatus());

    }

    @Test
    public void modNoteTest(){
        Date date = new Date();
        Note note = new Note(1,"test","laesunk",date,"cloudy","21c","에덴농장","테스트입니다","무4개","3000","비료","20000");
        Entity<Note> noteEntity = Entity.entity(note, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/note/notes/3").request().put(noteEntity);
        Assert.assertNotNull(response);
        Assert.assertEquals("무4개",getNote(2).getConsumeTitle());
        Assert.assertEquals(200,response.getStatus());
    }

    @Test
    public void deleteNoteTest() {
        Response response = target("/note/notes/4").request().delete();
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.getStatus());
    }

    public Note getNote(int id){
        DbHandler conn = DbHandler.getInstance();
        StringBuilder query = new StringBuilder("select * from note where id=?");
        Connection con = conn.getConnection();
        ResultSet rs = null;
        Note note = new Note();
        try {
            PreparedStatement ptmt = con.prepareStatement(query.toString());
            ptmt.setInt(1, id);
            rs = ptmt.executeQuery();
            rs.next();
            note.setId(rs.getInt(1));
            note.setTitle(rs.getString(2));
            note.setWriter(rs.getString(3));
            note.setWriteDate(rs.getTimestamp(4));
            note.setWeather(rs.getString(5));
            note.setTemperature(rs.getString(6));
            note.setPlace(rs.getString(7));
            note.setContent(rs.getString(8));
            note.setConsumeTitle(rs.getString(9));
            note.setIncomeTitle(rs.getString(10));
            note.setConsume(rs.getString(11));
            note.setIncome(rs.getString(12));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return note;
    }
}
