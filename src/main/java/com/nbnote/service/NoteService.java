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
       StringBuilder query = new StringBuilder("select id, title, writer, writeDate, weather, "
       		+ "temperature, place, content, consumeTitle, consume, "
       		+ "incomeTitle, income from note where writer=?");
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
                note.setConsume(rs.getInt(10));
                note.setIncomeTitle(rs.getString(11));
                note.setIncome(rs.getInt(12));
                
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


    public Note getNote(String userId, int noteId){
        Note note = new Note();
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

        return note;
    }

    public int writeNode(Note note) {
        StringBuilder query = new StringBuilder("insert into note(title, writer, writeDate, weather, temperature, place, content, " +
                "consumeTitle, incomeTitle, consume, income) values (?,?,now(),?,?,?,?,?,?,?,?)" );
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1,note.getTitle());
            ptmt.setString(2,note.getWriter());
            ptmt.setString(3,note.getWeather());
            ptmt.setString(4,note.getTemperature());
            ptmt.setString(5,note.getPlace());
            ptmt.setString(6,note.getContent());
            ptmt.setString(7,note.getConsumeTitle());
            ptmt.setString(8,note.getIncomeTitle());
            ptmt.setInt(9,note.getConsume());
            ptmt.setInt(10,note.getIncome());
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
        StringBuilder query = new StringBuilder("update note set title=?, place=?, content=?, consumeTitle=?, incomeTitle=?, consume=?, income=? WHERE id=?;");

        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement("update note set title=?, place=?, content=?, consumeTitle=?, incomeTitle=?, consume=?, income=? WHERE id=?;");
            ptmt.setString(1,note.getTitle());
            ptmt.setString(2,note.getPlace());
            ptmt.setString(3,note.getContent());
            ptmt.setString(4,note.getConsumeTitle());
            ptmt.setString(5,note.getIncomeTitle());
            ptmt.setInt(6,note.getConsume());
            ptmt.setInt(7,note.getIncome());
            ptmt.setInt(8, note.getId());

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
