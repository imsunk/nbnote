package com.nbnote.service;

import com.nbnote.db.DbHandler;
import com.nbnote.model.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by K on 2017. 6. 14..
 */
public class NoteService extends Service{
    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    public ArrayList<Note> getAllNote(String userId){
       ArrayList<Note>  notes = new ArrayList<Note>();
       StringBuilder query = new StringBuilder("select * from note where writer=?");
       Connection con = conn.getConnection();
       ResultSet rs = null;
       PreparedStatement ptmt = null;
       try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, userId);
            rs = ptmt.executeQuery();
            while(rs.next()) {
                Note note = new Note();
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
                notes.add(note);
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } finally {
            try{
                rs.close();
                ptmt.close();
                con.close();
            }catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }

       return notes;
    }


    public ArrayList<Note> getNote(String userId, int noteId){
        ArrayList<Note>  notes = new ArrayList<Note>();
        StringBuilder query = new StringBuilder("select * from note where writer=? and id=?");
        Connection con = conn.getConnection();
        ResultSet rs = null;
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, userId);
            ptmt.setInt(2, noteId);
            rs = ptmt.executeQuery();
            rs.next();
            Note note = new Note();
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
            logger.debug(e.getMessage());
        } finally {
            try{
                rs.close();
                ptmt.close();
                con.close();
            }catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }

        return notes;
    }

    public int writeNode(Note note) {
        StringBuilder query = new StringBuilder("insert into note(title, writer, writeDate, weather, temperature, place, content, " +
                "consumeTitle, incomeTitle, consume, income) values (?,?,?,?,?,?,?,?,?,?,?)" );
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1,note.getTitle());
            ptmt.setString(2,note.getWriter());
            ptmt.setTimestamp(3,new java.sql.Timestamp(note.getWriteDate().getTime()));
            ptmt.setString(4,note.getWeather());
            ptmt.setString(5,note.getTemperature());
            ptmt.setString(6,note.getPlace());
            ptmt.setString(7,note.getContent());
            ptmt.setString(8,note.getConsumeTitle());
            ptmt.setString(9,note.getIncomeTitle());
            ptmt.setString(10,note.getConsume());
            ptmt.setString(11,note.getIncome());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            return 1;
        } finally {
            try{
                ptmt.close();
                con.close();
            }catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }

        return 0;

    }

    public int modNote(Note note,int id) {
        StringBuilder query = new StringBuilder("update note set title=?, place=?, content=?, consumeTitle=?, incomeTitle=?, income=?, consume=? WHERE id=?");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1,note.getTitle());
            ptmt.setString(2,note.getPlace());
            ptmt.setString(3,note.getContent());
            ptmt.setString(4,note.getConsumeTitle());
            ptmt.setString(5,note.getIncomeTitle());
            ptmt.setString(6,note.getIncome());
            ptmt.setString(7,note.getConsume());
            ptmt.setInt(8, id);

            ptmt.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            return 1;
        } finally {
            try{
                ptmt.close();
                con.close();
            }catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }

        return 0;
    }

    public void deleteNote(int noteId){
        StringBuilder query = new StringBuilder("DELETE FROM note WHERE id=?" );
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setInt(1,noteId);
            ptmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                ptmt.close();
                con.close();
            }catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }
    }

}
