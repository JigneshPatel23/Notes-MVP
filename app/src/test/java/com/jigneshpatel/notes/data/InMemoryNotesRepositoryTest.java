package com.jigneshpatel.notes.data;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created on 8/2/16.
 */
public class InMemoryNotesRepositoryTest {

    private final static String NOTE_TITLE = "title";

    private static List<Note> NOTES = Lists.newArrayList(new Note("Title1", "Description1"),
            new Note("Title2", "Description2"));

    private InMemoryNotesRepository mNotesRepository;

    @Mock
    private NotesServiceApiImpl mServiceApi;

    @Mock
    private NotesRepository.GetNoteCallback mGetNoteCallback;

    @Mock
    private NotesRepository.LoadNotesCallback mLoadNotesCallback;

    @Captor
    private ArgumentCaptor<NotesServiceApi.NotesServiceCallback> mNotesServiceCallbackCaptor;

    @Before
    public void setupNotesRepository() {
        MockitoAnnotations.initMocks(this);
        mNotesRepository = new InMemoryNotesRepository(mServiceApi);
    }

    @Test
    public void getNotes_repositoryCachesAfterFirstApiCall() {
        twoLoadCallsToRepository(mLoadNotesCallback);
        verify(mServiceApi).getAllNotes(any(NotesServiceApi.NotesServiceCallback.class));
    }

    @Test
    public void invalidateCache_DoesNotCallTheServiceApi() {
        twoLoadCallsToRepository(mLoadNotesCallback);

        mNotesRepository.refreshData();
        mNotesRepository.getNotes(mLoadNotesCallback);

        verify(mServiceApi, times(2)).getAllNotes(any(NotesServiceApi.NotesServiceCallback.class));
    }

    @Test
    public void getNotes_requestsAllNotesFromServiceApi() {
        mNotesRepository.getNotes(mLoadNotesCallback);

        verify(mServiceApi).getAllNotes(any(NotesServiceApi.NotesServiceCallback.class));
    }

    @Test
    public void saveNote_savesNoteToServiceAPIAndInvalidatesCache() {
        Note newNote = new Note(NOTE_TITLE, "Some Note Description");

        mNotesRepository.saveNote(newNote);

        assertThat(mNotesRepository.mCachedNotes, is(nullValue()));
    }

    @Test
    public void getNote_requestsSingleNoteFromServiceApi() {
        mNotesRepository.getNote(NOTE_TITLE, mGetNoteCallback);

        verify(mServiceApi).getNote(eq(NOTE_TITLE), any(NotesServiceApi.NotesServiceCallback.class));
    }

    private void twoLoadCallsToRepository(NotesRepository.LoadNotesCallback callback) {
        mNotesRepository.getNotes(callback);

        verify(mServiceApi).getAllNotes(mNotesServiceCallbackCaptor.capture());

        mNotesServiceCallbackCaptor.getValue().onLoaded(NOTES);

        mNotesRepository.getNotes(callback);
    }

}
