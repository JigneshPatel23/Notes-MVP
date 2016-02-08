package com.jigneshpatel.notes.data;

import java.util.List;

/**
 * Created on 8/2/16.
 */
public interface NotesServiceApi {

    interface NotesServiceCallback<T>{
        void onLoaded(T notes);
    }

    void getAllNotes(NotesServiceCallback<List<Note>> callback);

    void getNote(String noteId, NotesServiceCallback<Note> callback);

    void saveNote(Note note);
}
