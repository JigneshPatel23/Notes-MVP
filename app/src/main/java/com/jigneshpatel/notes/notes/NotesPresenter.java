package com.jigneshpatel.notes.notes;
import android.support.annotation.NonNull;

import com.jigneshpatel.notes.data.Note;
import com.jigneshpatel.notes.data.NotesRepository;

/**
 * Created on 8/2/16.
 */
public class NotesPresenter implements NotesContract.UserActionListener {

    private final NotesRepository mNotesRepository;
    private final NotesContract.View mNotesView;


    public NotesPresenter(NotesRepository notesRepository, NotesContract.View notesView) {
        mNotesRepository = notesRepository;
        mNotesView = notesView;
    }

    @Override
    public void loadNotes(boolean forceUpdate) {
    }

    @Override
    public void addNewNote() {
    }

    @Override
    public void openNoteDetails(@NonNull Note note) {
    }
}
