package com.jigneshpatel.notes.addnote;

import android.support.annotation.NonNull;

import java.io.IOException;

/**
 * Created on 8/2/16.
 */
public interface AddNoteContract {

    interface View{
        void showEmptyNoteError();
        void showNotesList();
        void openCamera(String saveTo);
        void showImagePreview(@NonNull String url);
        void showImageError();
        void setUserActionListener(UserActionListener listener);
    }

    interface UserActionListener{
        void saveNote(String title, String description);
        void takePicture() throws IOException;
        void imageAvailable();
        void imageCaptureFailed();
    }
}
