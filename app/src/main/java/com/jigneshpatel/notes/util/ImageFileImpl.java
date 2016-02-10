package com.jigneshpatel.notes.util;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.VisibleForTesting;

import java.io.File;
import java.io.IOException;

/**
 * Created on 8/2/16.
 */
public class ImageFileImpl implements ImageFile {

    @VisibleForTesting
    File mImageFile;

    @Override
    public void create(String name, String extension) throws IOException {
        File storageDir =  Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        mImageFile = File.createTempFile(name, extension, storageDir);
    }

    @Override
    public boolean exists() {
        return null != mImageFile && mImageFile.exists();
    }

    @Override
    public void delete() {
        mImageFile = null;
    }

    @Override
    public String getPath() {
        return Uri.fromFile(mImageFile).toString();
    }
}
