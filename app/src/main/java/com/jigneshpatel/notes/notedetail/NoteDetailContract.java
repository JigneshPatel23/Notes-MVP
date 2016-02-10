package com.jigneshpatel.notes.notedetail;

import android.support.annotation.Nullable;

/**
 * Created on 8/2/16.
 */
public interface NoteDetailContract {

    interface View{
        void setProgressIndicator(boolean active);
        void showMissingNoteUi();
        void showTitle(String title);
        void hideTitle();
        void showDescription(String description);
        void hideDescription();
        void showImage(String imageUrl);
        void hideImage();
    }

    interface UserActionListener{
        void openNote(@Nullable String noteId);
    }
}
