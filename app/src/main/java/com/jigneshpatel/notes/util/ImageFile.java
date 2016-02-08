package com.jigneshpatel.notes.util;

import java.io.IOException;

/**
 * Created on 8/2/16.
 */
public interface ImageFile {

    void create(String name, String extension) throws IOException;

    boolean exists();

    void delete();

    String getPath();
}
