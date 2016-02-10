package com.jigneshpatel.notes.addnote;

import com.jigneshpatel.notes.data.NotesRepository;
import com.jigneshpatel.notes.util.ImageFile;

import java.io.IOException;

/**
 * Created on 8/2/16.
 */
public class AddNotePresenter implements AddNoteContract.UserActionListener {

    private final NotesRepository mNotesRepository;
    private final AddNoteContract.View mAddNoteView;
    private final ImageFile mImageFile;

    public AddNotePresenter(NotesRepository notesRepository, AddNoteContract.View addNoteView, ImageFile imageFile) {
        mNotesRepository = notesRepository;
        mAddNoteView = addNoteView;
        mImageFile = imageFile;
    }

    @Override
    public void saveNote(String title, String description) {

    }

    @Override
    public void takePicture() throws IOException {

    }

    @Override
    public void imageAvailable() {

    }

    @Override
    public void imageCaptureFailed() {

    }
}
