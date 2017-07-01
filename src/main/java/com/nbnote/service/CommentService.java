package com.nbnote.service;

import com.nbnote.model.Comment;
import com.nbnote.model.Note;
import com.nbnote.model.Reply;
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
    /*
    public ArrayList<Comment> getAllComments(String userId, int noteId) {
        StringBuilder query = new StringBuilder("select t1.noteId, t1.id, t1.writerId as commentWriter, t1.content as comment, t1.writeDate commentDate, " +
                "t2.writeDate as replyDate, t2.writer as replyWriter, t2.content as reply");
        query.append("FROM comment t1");
        query.append("LEFT OUTER JOIN reply t2");
        query.append("on t1.id=t2.commentId where t1.noteId=? and t1.writer=?");
        query.append("order by t1.id");

        ArrayList<Comment> comments = new ArrayList<>();
        Connection con = conn.getConnection();
        ResultSet rs = null;

        try {
            PreparedStatement ptmt = con.prepareStatement(query.toString());
            ptmt.setInt(1, noteId);
            ptmt.setString(2, userId);

            rs = ptmt.executeQuery();
            int beforeId = 0;
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setNoteId(rs.getInt(2));
                comment.setId(rs.getInt(1));

                comment.setParentId(rs.getInt(3));
                comment.setWriterId(rs.getString(4));
                comment.setWriteDate(rs.getTimestamp(5));
                comment.setContent(rs.getString(6));
                beforeId = rs.getInt(1)
            }
        }catch(SQLException e){
            logger.debug(e.getMessage());
        }
        return comments;
    }*/

    public ArrayList<Comment> getAllComments(String userId, int noteId) {
        StringBuilder query = new StringBuilder("select * from comment where noteId=? order by if((parentid=0) ,id,parentId), writeDate ");
        ArrayList<Comment> comments = new ArrayList<>();
        Connection con = conn.getConnection();
        ResultSet rs = null;

        try {
            PreparedStatement ptmt = con.prepareStatement(query.toString());
            ptmt.setInt(1, noteId);

            rs = ptmt.executeQuery();
            int beforeId = 0;
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt(1));
                comment.setNoteId(rs.getInt(2));
                comment.setParentId(rs.getInt(3));
                comment.setWriterId(rs.getString(4));
                comment.setWriteDate(rs.getTimestamp(5));
                comment.setContent(rs.getString(6));

                comments.add(comment);
            }
        }catch(SQLException e){
            logger.debug(e.getMessage());
        }
        return comments;
    }

    public void writeComment(Comment comment){
        StringBuilder query = new StringBuilder("insert into comment(noteId, parentId, writeDate, writer, content) values(?,0,?,?,?)");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setInt(1,comment.getNoteId());
            ptmt.setTimestamp(2,new java.sql.Timestamp(comment.getWriteDate().getTime()));
            ptmt.setString(3,comment.getWriterId());
            ptmt.setString(4,comment.getContent());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public void writeReply(Comment comment){
        StringBuilder query = new StringBuilder("insert into comment(noteId, parentId, writeDate, writer, content) values(?,?,?,?,?)");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setInt(1,comment.getNoteId());
            ptmt.setInt(2,comment.getParentId());
            ptmt.setTimestamp(3,new java.sql.Timestamp(comment.getWriteDate().getTime()));
            ptmt.setString(4,comment.getWriterId());
            ptmt.setString(5,comment.getContent());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public void deleteReply(int noteId, int commentId){
        StringBuilder query = new StringBuilder("delete from comment where noteId=? and id=?)");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setInt(1,noteId);
            ptmt.setInt(2,commentId);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public void updateComment(Comment comment){
        StringBuilder query = new StringBuilder("update from comment set content=? where noteId=? and id=?)");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1,comment.getContent());
            ptmt.setInt(2,comment.getNoteId());
            ptmt.setInt(3,comment.getId());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }
}
