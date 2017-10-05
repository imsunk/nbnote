package com.nbnote.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import com.nbnote.conf.Configuration;
import com.nbnote.db.DbHandler;
import com.nbnote.model.Note;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by K on 2017. 6. 18..
 */
public class NoteControllerTest extends JerseyTest{
    @Override
    protected Application configure() {
        return  new ResourceConfig(NoteController.class);
    }

    @Test
    public void AllNoteListTest(){
        Response response = target("/note/users/lskim").request().get();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
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
        Note note = new Note(1,"test","laesunk",date,"sunny","21c","에덴농장","테스트입니다","무3개",3000,"비료",20000);
        Entity<Note> noteEntity = Entity.entity(note, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/note").request().post(noteEntity);
        Assert.assertNotNull(response);

        Assert.assertEquals(200,response.getStatus());

    }

    @Test
    public void modNoteTest(){
        Date date = new Date();
        Note note = new Note(1,"test","laesunk",date,"cloudy","21c","에덴농장","테스트입니다","무4개",3000,"비료",20000);
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
    
    @Test
	public void uploadFileTest() throws IOException {
    	String testFilePath = Configuration.getConf(Configuration.TEST_UPLOAD_FILE);
    	
		MultipartBody m = new MultipartBody.Builder().setType(MultipartBody.FORM)
				.addFormDataPart("fileUpload", "testFile.txt", 
						RequestBody.create(okhttp3.MediaType.parse("application/octet-stream"), new File(testFilePath))).build();

		OkHttpClient okhttp = new OkHttpClient();
		
		String uploadUrl = Configuration.getConf(Configuration.SERVER_URI_ROOT)+"api/note/file";

		Request request = new Request.Builder().url(Configuration.getConf(Configuration.SERVER_URI_ROOT)+"api/note/file").post(m).build();
		okhttp3.Response res = okhttp.newCall(request).execute();


		Assert.assertNotNull(res);
		Assert.assertEquals(200, res.code());
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
            note.setConsume(rs.getInt(11));
            note.setIncome(rs.getInt(12));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return note;
    }
}
