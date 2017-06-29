package com.nbnote.model;

import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017. 6. 26..
 */
public class Comment {
    private int id;
    private int noteId;
    private int parentId;
    private String writerId;
    private Date writeDate;
    //private List<Reply> replies;
    private String content;
    private int commentGroup;

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getCommentGroup() {
        return commentGroup;
    }

    public void setCommentGroup(int commentGroup) {
        this.commentGroup = commentGroup;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }
    /*
    public List<com.nbnote.model.Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<com.nbnote.model.Reply> replies) {
        this.replies = replies;
    }
    */

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
