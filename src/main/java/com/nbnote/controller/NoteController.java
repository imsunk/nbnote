package com.nbnote.controller;

import com.nbnote.model.Note;
import com.nbnote.model.UploadPicture;
import com.nbnote.service.NoteService;
import com.nbnote.service.UploadPictureService;
import com.nbnote.util.FileUtil;
import com.nbnote.util.ImageUtil;
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by K on 2017. 6. 14..
 */

@Path("/note")
public class NoteController extends BaseController{
    private static Logger LOG = LoggerFactory.getLogger(NoteController.class);
    public static final String UPLOAD_FILE_SERVER = "D:/Demo/upload/";
    private  NoteService noteService = new NoteService();
    private UploadPictureService upPictureSvc = new UploadPictureService();

    @POST
    @Consumes("application/json")
    public Response writeNote(Note note){
        int result = noteService.writeNode(note);
        if (result==1){
            return Response.status(500).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public ArrayList<Note> getNoteList(@PathParam("userId")String userId){
        ArrayList<Note> noteList;
        noteList = noteService.getAllNote(userId);

        return noteList;
    }

    @GET
    @Path("/users/{userId}/notes/{noteId}")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public ArrayList<Note> getNote(@PathParam("userId")String userId, @PathParam("noteId")int noteId){
        ArrayList<Note> noteList;
        noteList = noteService.getNote(userId,noteId);

        return noteList;
    }

    @PUT
    @Path("/notes/{noteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modNote(Note note, @PathParam("noteId")int id) {
        int result = noteService.modNote(note,id);
        if (result==1){
            return Response.status(500).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/notes/{noteId}")
    public Response deleteNote(@PathParam("noteId")int noteId){
        noteService.deleteNote(noteId);

        return Response.ok().build();
    }

    /*
    @POST
    @Path("/thumbnail")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public UploadPicture uploadPicture(@Context HttpServletRequest request) throws Exception {
        UploadPicture result = upPictureSvc.uploadNotePicture(request);

        FileUtil result
        File tmp = new File(es.util.Path.userUploadTmpDir(result.getFilename()));
        if(tmp != null){
            File parentFile = tmp.getParentFile();
            if(parentFile != null) parentFile.mkdirs();
        }

        String snsImageName = null;
        try {
            snsImageName = ImageUtil.makeThumbnail(tmp.getAbsolutePath());
        } catch (IOException ie){
            ie.printStackTrace();
        }

        result.setFilename(FilenameUtils.getName(snsImageName));
        result.setFileUrl(es.util.Path.userUploadTmpUrl(FilenameUtils.getName(snsImageName)).replace("\\", "/"));

        return result;
    }
    */

}
