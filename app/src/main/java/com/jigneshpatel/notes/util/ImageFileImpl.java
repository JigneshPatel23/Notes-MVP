package com.jigneshpatel.notes.util;

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
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public void delete() {

    }

    @Override
    public String getPath() {
        return null;
    }
}
