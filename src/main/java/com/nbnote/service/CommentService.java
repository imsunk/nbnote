package com.nbnote.service;

import com.nbnote.model.Comment;
import com.nbnote.model.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by K on 2017. 6. 26..
 */
public class CommentService extends Service {
    private static Logger logger  = LoggerFactory.getLogger(CommentService.class);
    public ArrayList<Comment> getAllReply(String userId, int noteId) {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        StringBuilder query = new StringBuilder("select * from comment where writer=? and noteId=? and commentGroup=0");
        Connection con = conn.getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, userId);
            ptmt.setInt(2, noteId);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt(1));
                comment.setNoteId(rs.getInt(2));
                comment.setParentId(rs.getInt(3));
                comment.setWriterId(rs.getString(4));
                comment.setWriteDate(rs.getTimestamp(5));
                comment.setContent(rs.getString(6));
                comment.setCommentGroup(rs.getInt(7));
            }
        }catch(SQLException e){
            logger.debug(e.getMessage());
        }
        return comments;
    }
    /*
    public void writeComment(Comment comment){
        StringBuilder query = new StringBuilder("insert into comment(id, noteId, writeDate, writer, content) values(?,?,?,?,?);
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
        }
    } */
}
