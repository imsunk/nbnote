package com.nbnote.util;

import com.oreilly.servlet.multipart.FileRenamePolicy;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by K on 2017. 7. 8..
 */
public class PicutreRename implements FileRenamePolicy {
    public File rename(File f) {
        String newName = null;
        try {
            newName = UUID.randomUUID().toString().replace("-", "")
                    + "_"
                    + URLEncoder.encode(FilenameUtils.getBaseName(f.getName()), "UTF-8")
                    + "."
                    + FilenameUtils.getExtension(f.getName()).toLowerCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new File(f.getParent(), newName);
    }
}
