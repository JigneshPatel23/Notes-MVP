package com.jigneshpatel.notes.addnote;

import android.support.annotation.NonNull;

import com.jigneshpatel.notes.data.Note;
import com.jigneshpatel.notes.data.NotesRepository;
import com.jigneshpatel.notes.util.ImageFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 8/2/16.
 */
public class AddNotePresenter implements AddNoteContract.UserActionListener {

    @NonNull
    private final NotesRepository mNotesRepository;
    @NonNull
    private final AddNoteContract.View mAddNoteView;
    @NonNull
    private final ImageFile mImageFile;

    public AddNotePresenter(@NonNull NotesRepository notesRepository,
                            @NonNull AddNoteContract.View addNoteView,
                            @NonNull ImageFile imageFile) {
        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null!");
        mAddNoteView = checkNotNull(addNoteView, "addNoteView cannot be null!");
        mImageFile = checkNotNull(imageFile, "imageFile cannot be null");
    }

    @Override
    public void saveNote(String title, String description) {
        String imageUrl = null;
        if(mImageFile.exists()){
            imageUrl = mImageFile.getPath();
        }

        Note newNote = new Note(title, description, imageUrl);
        if(newNote.isEmpty()){
            mAddNoteView.showEmptyNoteError();
        }else {
            mNotesRepository.saveNote(newNote);
            mAddNoteView.showNotesList();
        }
    }

    @Override
    public void takePicture() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_"+timeStamp+"_";
        mImageFile.create(imageFileName,".jpg");
        mAddNoteView.openCamera(mImageFile.getPath());
    }

    @Override
    public void imageAvailable() {
        if(mImageFile.exists()){
            mAddNoteView.showImagePreview(mImageFile.getPath());
        }else{
            imageCaptureFailed();
        }
    }

    @Override
    public void imageCaptureFailed() {
        mImageFile.delete();
        mAddNoteView.showImageError();
    }
}
