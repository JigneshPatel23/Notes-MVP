package com.jigneshpatel.notes.notes;

import com.google.common.collect.Lists;
import com.jigneshpatel.notes.data.Note;
import com.jigneshpatel.notes.data.NotesRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created on 8/2/16.
 */
public class NotesPresenterTest {

    private static List<Note> NOTES = Lists.newArrayList(new Note("Title1", "Description1"),
            new Note("Title2", "Description2"));

    @Mock
    private NotesRepository mNotesRepository;

    @Mock
    private NotesContract.View mNotesView;

    @Captor
    private ArgumentCaptor<NotesRepository.LoadNotesCallback> mLoadNotesCallbackCaptor;

    private NotesPresenter mNotesPresenter;

    @Before
    public void setupNotesPresenter(){
        MockitoAnnotations.initMocks(this);

        mNotesPresenter = new NotesPresenter(mNotesRepository, mNotesView);
    }

    @Test
    public void loadNotesFromRepoAndLoadIntoView(){
        mNotesPresenter.loadNotes(true);

        verify(mNotesRepository).getNotes(mLoadNotesCallbackCaptor.capture());
        mLoadNotesCallbackCaptor.getValue().onNotesLoaded(NOTES);

        verify(mNotesView).setProgressIndicator(false);
        verify(mNotesView).showNotes(NOTES);
    }

    @Test
    public void clickOnFab_ShowsAddNoteUi(){
        mNotesPresenter.addNewNote();
        verify(mNotesView).showAddNoteUi();
    }

    @Test
    public void clickOnNote_ShowDetailUi(){
        Note note = new Note("Details Requested", "For this note");
        mNotesPresenter.openNoteDetails(note);
        verify(mNotesView).showNoteDetailUi(any(String.class));
    }
}
