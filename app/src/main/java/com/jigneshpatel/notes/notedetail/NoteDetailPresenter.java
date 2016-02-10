package com.jigneshpatel.notes.notedetail;

import android.support.annotation.Nullable;

import com.jigneshpatel.notes.data.Note;
import com.jigneshpatel.notes.data.NotesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 8/2/16.
 */
public class NoteDetailPresenter implements NoteDetailContract.UserActionListener {

    private final NotesRepository mNotesRepository;
    private final NoteDetailContract.View mNoteDetailView;

    public NoteDetailPresenter(NotesRepository notesRepository, NoteDetailContract.View noteDetailView) {
        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null!");
        mNoteDetailView = checkNotNull(noteDetailView, "noteDetailView cannot be null!");
    }

    @Override
    public void openNote(@Nullable String noteId) {
        if(noteId == null || noteId.isEmpty()){
            mNoteDetailView.showMissingNoteUi();
            return;
        }

        mNoteDetailView.setProgressIndicator(true);
        mNotesRepository.getNote(noteId, new NotesRepository.GetNoteCallback() {
            @Override
            public void onNoteLoaded(Note note) {
                mNoteDetailView.setProgressIndicator(false);
                if(null == note){
                    mNoteDetailView.showMissingNoteUi();
                }else{
                    showNote(note);
                }
            }
        });
    }

    private void showNote(Note note) {
        String title = note.getTitle();
        String description = note.getDescription();
        String imageUrl = note.getImageUrl();

        if(title == null || title.isEmpty()){
            mNoteDetailView.hideTitle();
        }else{
            mNoteDetailView.showTitle(title);
        }

        if(description == null || description.isEmpty()){
            mNoteDetailView.hideDescription();
        }else{
            mNoteDetailView.showDescription(description);
        }


        if(imageUrl == null || imageUrl.isEmpty()){
            mNoteDetailView.hideImage();
        }else{
            mNoteDetailView.showImage(imageUrl);
        }
    }
}
