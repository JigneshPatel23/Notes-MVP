package com.jigneshpatel.notes.notedetail;

import android.support.annotation.Nullable;

import com.jigneshpatel.notes.data.NotesRepository;

/**
 * Created on 8/2/16.
 */
public class NoteDetailPresenter implements NoteDetailContract.UserActionListener {

    private final NotesRepository mNotesRepository;
    private final NoteDetailContract.View mNoteDetailView;

    public NoteDetailPresenter(NotesRepository notesRepository, NoteDetailContract.View noteDetailView) {
        mNotesRepository = notesRepository;
        mNoteDetailView = noteDetailView;
    }

    @Override
    public void openNote(@Nullable String noteId) {

    }
}
