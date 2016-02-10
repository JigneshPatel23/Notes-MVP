package com.jigneshpatel.notes.notes;

import android.support.annotation.NonNull;

import com.jigneshpatel.notes.data.Note;

import java.util.List;

/**
 * Created on 8/2/16.
 */
public interface NotesContract {

    interface View{
        void setProgressIndicator(boolean active);
        void showNotes(List<Note> notes);
        void showAddNoteUi();
        void showNoteDetailUi(String noteId);
    }

    interface UserActionsListener{
        void loadNotes(boolean forceUpdate);
        void addNewNote();
        void openNoteDetails(@NonNull Note note);
    }
}
