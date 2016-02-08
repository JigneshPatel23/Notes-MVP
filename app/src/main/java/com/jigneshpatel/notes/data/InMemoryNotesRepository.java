package com.jigneshpatel.notes.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.List;

/**
 * Created on 8/2/16.
 */
public class InMemoryNotesRepository implements NotesRepository {

    private final NotesServiceApi mNotesServiceApi;

    @VisibleForTesting
    List<Note> mCachedNotes;

    public InMemoryNotesRepository(@NonNull NotesServiceApi notesServiceApi) {
        mNotesServiceApi = notesServiceApi;
    }

    @Override
    public void getNotes(@NonNull LoadNotesCallback callback) {

    }

    @Override
    public void getNote(@NonNull String noteId, @NonNull GetNoteCallback callback) {

    }

    @Override
    public void saveNote(@NonNull Note note) {

    }

    @Override
    public void refreshData() {

    }
}
