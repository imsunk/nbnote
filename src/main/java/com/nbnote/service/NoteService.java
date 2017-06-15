package com.nbnote.service;

import com.nbnote.db.DBConnectionHandler;
import com.nbnote.model.Note;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by K on 2017. 6. 14..
 */
public class NoteService {
    private ConcurrentHashMap<Integer, Note> note = new ConcurrentHashMap<Integer, Note>();
    private DBConnectionHandler connect = DBConnectionHandler.getConnection();
    private AtomicInteger maxId = new AtomicInteger(0);
    public ArrayList<Note> getAllNote(String id){
       ArrayList<Note>  notes = new ArrayList<Note>();
    }
    public Note create(Note note) {
        return null;
    }


}
