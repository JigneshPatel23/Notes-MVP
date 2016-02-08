package com.jigneshpatel.notes.notedetail;

import com.jigneshpatel.notes.data.Note;
import com.jigneshpatel.notes.data.NotesRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created on 8/2/16.
 */
public class NotesDetailPresenterTest {

    public static final String INVALID_ID ="INVALID_ID";
    public static final String TITLE_TEST = "title";
    public static final String DESCRIPTION_TEST = "description";

    @Mock
    private NotesRepository mNotesRepository;

    @Mock
    private NoteDetailContract.View mNoteDetailView;

    @Captor
    private ArgumentCaptor<NotesRepository.GetNoteCallback> mGetNoteCallbackCaptor;

    private NoteDetailPresenter mNoteDetailPresenter;

    @Before
    public void setupNotesPresenter(){
        MockitoAnnotations.initMocks(this);
        mNoteDetailPresenter = new NoteDetailPresenter(mNotesRepository, mNoteDetailView);
    }

    @Test
    public void getNoteFromRepoAndLoadIntoView(){
        Note note = new Note(TITLE_TEST, DESCRIPTION_TEST);
        mNoteDetailPresenter.openNote(note.getId());
        verify(mNotesRepository).getNote(eq(note.getTitle()),mGetNoteCallbackCaptor.capture());
        verify(mNoteDetailView).setProgressIndicator(true);

        mGetNoteCallbackCaptor.getValue().onNoteLoaded(note);

        verify(mNoteDetailView).setProgressIndicator(false);
        verify(mNoteDetailView).showTitle(TITLE_TEST);
        verify(mNoteDetailView).showDescription(DESCRIPTION_TEST);
    }

    @Test
    public void getUnknownNoteFromRepoAndLoadIntoView(){
        mNoteDetailPresenter.openNote(INVALID_ID);

        verify(mNoteDetailView).setProgressIndicator(true);
        verify(mNotesRepository).getNote(eq(INVALID_ID), mGetNoteCallbackCaptor.capture());

        mGetNoteCallbackCaptor.getValue().onNoteLoaded(null);

        verify(mNoteDetailView).setProgressIndicator(false);
        verify(mNoteDetailView).showMissingNoteUi();
    }
}
