package com.jigneshpatel.notes.addnote;

import com.jigneshpatel.notes.data.Note;
import com.jigneshpatel.notes.data.NotesRepository;
import com.jigneshpatel.notes.util.ImageFile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created on 8/2/16.
 */
public class AddNotePresenterTest {

    @Mock
    private NotesRepository mNotesRepository;

    @Mock
    private ImageFile mImageFile;

    @Mock
    private AddNoteContract.View mAddNoteView;

    private AddNotePresenter mAddNotePresenter;

    @Before
    public void setupAddNotePresenter(){
        MockitoAnnotations.initMocks(this);

        mAddNotePresenter = new AddNotePresenter(mNotesRepository, mAddNoteView, mImageFile);
    }

    @Test
    public void saveNoteToRepo_showsSuccessUi(){
        mAddNotePresenter.saveNote("New Note Title", "Some Note Description");

        verify(mNotesRepository).saveNote(any(Note.class));
        verify(mAddNoteView).showNotesList();
    }

    @Test
    public void saveNote_emptyNoteShowsErrorUi(){
        mAddNotePresenter.saveNote("", "");

        verify(mAddNoteView).showEmptyNoteError();
    }

    @Test
    public void takePicture_CreatesFileAndOpenCamera() throws IOException{
        mAddNotePresenter.takePicture();

        verify(mImageFile).create(anyString(), anyString());
        verify(mImageFile).getPath();
        verify(mAddNoteView).openCamera(anyString());
    }

    @Test
    public void imageAvailable_SavesImageAndUpdatesUiWithThumbnail(){
        String imageUrl = "path/to/file";
        when(mImageFile.exists()).thenReturn(true);
        when(mImageFile.getPath()).thenReturn(imageUrl);

        mAddNotePresenter.imageAvailable();

        verify(mAddNoteView).showImagePreview(contains(imageUrl));
    }

    @Test
    public void imageAvailable_FileDoesNotExistShowsErrorUi(){
        when(mImageFile.exists()).thenReturn(false);

        mAddNotePresenter.imageAvailable();

        verify(mAddNoteView).showImageError();
        verify(mImageFile).delete();
    }

    @Test
    public void noImageAvailable_ShowErrorUi(){
        mAddNotePresenter.imageCaptureFailed();

        verify(mAddNoteView).showImageError();
        verify(mImageFile).delete();
    }
}
