package com.jlmd.android.evernoteclient.app.di;

import com.evernote.client.android.EvernoteSession;
import com.jlmd.android.evernoteclient.app.navigator.Navigator;
import com.jlmd.android.evernoteclient.domain.interactor.noteslist.GetNotesList;
import com.jlmd.android.evernoteclient.presentation.login.LoginPresenter;
import com.jlmd.android.evernoteclient.presentation.main.MainPresenter;
import com.jlmd.android.evernoteclient.presentation.noteslist.NotesListPresenter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * @author jlmd
 */
@Module(
    complete = false,
    library = true)
public final class PresenterModule {

  @Provides
  @Singleton
  LoginPresenter provideLoginPresenter(Navigator navigator) {
    return new LoginPresenter(navigator);
  }

  @Provides
  @Singleton
  MainPresenter provideMainPresenter(Navigator navigator, EvernoteSession evernoteSession) {
    return new MainPresenter(navigator, evernoteSession);
  }

  @Provides
  @Singleton
  NotesListPresenter provideNotesListPresenter(GetNotesList getNotesList) {
    return new NotesListPresenter(getNotesList);
  }
}