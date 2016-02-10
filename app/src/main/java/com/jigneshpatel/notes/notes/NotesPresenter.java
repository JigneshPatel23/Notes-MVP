package com.jigneshpatel.notes.notes;
import android.support.annotation.NonNull;

import com.jigneshpatel.notes.data.Note;
import com.jigneshpatel.notes.data.NotesRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 8/2/16.
 */
public class NotesPresenter implements NotesContract.UserActionsListener {

    private final NotesRepository mNotesRepository;
    private final NotesContract.View mNotesView;


    public NotesPresenter(@NonNull NotesRepository notesRepository,
                          @NonNull NotesContract.View notesView) {
        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null!");
        mNotesView = checkNotNull(notesView, "notesView cannot be null!");
    }

    @Override
    public void loadNotes(boolean forceUpdate) {
        mNotesView.setProgressIndicator(true);
        if(forceUpdate){
            mNotesRepository.refreshData();
        }

        mNotesRepository.getNotes(new NotesRepository.LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                mNotesView.setProgressIndicator(false);
                mNotesView.showNotes(notes);
            }
        });
    }

    @Override
    public void addNewNote() {
        mNotesView.showAddNoteUi();
    }

    @Override
    public void openNoteDetails(@NonNull Note note) {
        checkNotNull(note, "note cannot be null!");
        mNotesView.showNoteDetailUi(note.getId());
    }
}
