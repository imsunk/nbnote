package com.nbnote.controller;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nbnote.model.Note;
import com.nbnote.model.UploadPicture;
import com.nbnote.service.NoteService;
import com.nbnote.service.UploadPictureService;
import com.nbnote.util.FileUtil;
import com.nbnote.util.ImageUtil;
import com.nbnote.util.PathUtil;

/**
 * Created by K on 2017. 6. 14..
 */
@DeclareRoles({"admin", "user", "guest"})
@Path("/note")
public class NoteController extends BaseController{
    private static Logger LOG = LoggerFactory.getLogger(NoteController.class);
    public static final String UPLOAD_FILE_SERVER = "D:/Demo/upload/";
    private  NoteService noteService = new NoteService();
    private UploadPictureService upPictureSvc = new UploadPictureService();

    @POST
    @RolesAllowed({"user"})
    @Consumes("application/json")
    public Response writeNote(Note note){
        int result = noteService.writeNode(note);
        if (result==1){
            return Response.status(500).build();
        }
        return Response.ok().build();
    }

    @GET
    @RolesAllowed({"user"})
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public ArrayList<Note> getNoteList(@PathParam("userId")String userId){
        ArrayList<Note> noteList;
        noteList = noteService.getAllNote(userId);

        return noteList;
    }

    @GET
    @RolesAllowed({"user"})
    @Path("/users/{userId}/notes/{noteId}")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public Note getNote(@PathParam("userId")String userId, @PathParam("noteId")int noteId){
        Note note;
        note = noteService.getNote(userId,noteId);

        return note;
    }

    @PUT
    @RolesAllowed({"user"})
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
    @RolesAllowed({"user"})
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

        FileUtil fileUtil;
        File tmp = new File(PathUtil.userUploadTmpDir(result.getFilename()));
        if(tmp != null){
            File parentFile = tmp.getParentFile();
            if(parentFile != null) parentFile.mkdirs();
        }

        String snsImageName = null;
        snsImageName = ImageUtil.makeThumbnail(tmp.getAbsolutePath());

        result.setFilename(FilenameUtils.getName(snsImageName));
        result.setFileUrl(PathUtil.userUploadTmpUrl(FilenameUtils.getName(snsImageName)).replace("\\", "/"));

        return result;
    }
    */
}
