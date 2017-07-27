package com.nbnote.util;

import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.io.FileDeleteStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017. 7. 11..
 */
public class DeleteUploadCleaningTracker extends FileCleaningTracker {

    private List<String> filesToDelete = new ArrayList<>();

    public void deleteTemporaryFiles() {
        for (String file : filesToDelete) {
            new File(file).delete();
        }
        filesToDelete.clear();
    }

    @Override
    public synchronized void exitWhenFinished() {
        deleteTemporaryFiles();
    }

    @Override
    public int getTrackCount() {
        return filesToDelete.size();
    }

    @Override
    public void track(File file, Object marker) {
        filesToDelete.add(file.getAbsolutePath());
    }

    @Override
    public void track(File file, Object marker, FileDeleteStrategy deleteStrategy) {
        filesToDelete.add(file.getAbsolutePath());
    }

    @Override
    public void track(String path, Object marker) {
        filesToDelete.add(path);
    }

    @Override
    public void track(String path, Object marker, FileDeleteStrategy deleteStrategy) {
        filesToDelete.add(path);
    }
}
