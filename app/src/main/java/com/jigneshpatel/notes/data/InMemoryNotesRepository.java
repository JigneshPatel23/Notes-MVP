package com.jigneshpatel.notes.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 8/2/16.
 */
public class InMemoryNotesRepository implements NotesRepository {

    private final NotesServiceApi mNotesServiceApi;

    @VisibleForTesting
    List<Note> mCachedNotes;

    public InMemoryNotesRepository(@NonNull NotesServiceApi notesServiceApi) {
        mNotesServiceApi = checkNotNull(notesServiceApi);
    }

    @Override
    public void getNotes(@NonNull final LoadNotesCallback callback) {
        checkNotNull(callback);

        if(mCachedNotes == null){
            mNotesServiceApi.getAllNotes(new NotesServiceApi.NotesServiceCallback<List<Note>>() {
                @Override
                public void onLoaded(List<Note> notes) {
                    mCachedNotes = ImmutableList.copyOf(notes);
                    callback.onNotesLoaded(mCachedNotes);
                }
            });
        }else {
            callback.onNotesLoaded(mCachedNotes);
        }
    }

    @Override
    public void getNote(@NonNull String noteId, @NonNull final GetNoteCallback callback) {
        checkNotNull(noteId);
        checkNotNull(callback);

        mNotesServiceApi.getNote(noteId, new NotesServiceApi.NotesServiceCallback<Note>() {
            @Override
            public void onLoaded(Note note) {
                callback.onNoteLoaded(note);
            }
        });
    }

    @Override
    public void saveNote(@NonNull Note note) {
        checkNotNull(note);
        mNotesServiceApi.saveNote(note);
        refreshData();
    }

    @Override
    public void refreshData() {
        mCachedNotes = null;
    }
}
