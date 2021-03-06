package com.jlmd.android.evernoteclient.data.mapper;

import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteAttributes;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jlmd
 */
public class NotesToDomainMapperTest {

  @Test
  public void testSameTitle() {
    List<Note> notes = provideFakeNotes();
    List<com.jlmd.android.evernoteclient.domain.model.Note> mappedNotes =
        new NotesToDomainMapper().map(notes);
    assertEquals("title1", mappedNotes.get(0).getTitle());
    assertEquals("title2", mappedNotes.get(1).getTitle());
  }

  @Test
  public void testSameContent() {
    List<Note> notes = provideFakeNotes();
    List<com.jlmd.android.evernoteclient.domain.model.Note> mappedNotes =
        new NotesToDomainMapper().map(notes);
    assertEquals("content1", mappedNotes.get(0).getContent());
    assertEquals("content2", mappedNotes.get(1).getContent());
  }

  @Test
  public void testSameCreatedDate() {
    List<Note> notes = provideFakeNotes();
    List<com.jlmd.android.evernoteclient.domain.model.Note> mappedNotes =
        new NotesToDomainMapper().map(notes);
    assertEquals(1, mappedNotes.get(0).getCreated());
    assertEquals(2, mappedNotes.get(1).getCreated());
  }

  @Test
  public void testSameAuthor() {
    List<Note> notes = provideFakeNotes();
    List<com.jlmd.android.evernoteclient.domain.model.Note> mappedNotes =
        new NotesToDomainMapper().map(notes);
    assertEquals("author1", mappedNotes.get(0).getAuthor());
    assertEquals("author2", mappedNotes.get(1).getAuthor());
  }

  private List<Note> provideFakeNotes() {
    List<Note> notes = new ArrayList<>();
    notes.add(noteBuilder("title1", "content1", 1, "author1"));
    notes.add(noteBuilder("title2", "content2", 2, "author2"));
    return notes;
  }

  private Note noteBuilder(String title, String content, long created, String author) {
    String DNMLFormat =
        "<!DOCTYPE en-note SYSTEM 'http://xml.evernote.com/pub/enml2.dtd'><en-note>%s</en-note>";
    Note note = new Note();
    note.setTitle(title);
    note.setContent(String.format(DNMLFormat, content));
    note.setCreated(created);
    NoteAttributes noteAttributes = new NoteAttributes();
    noteAttributes.setAuthor(author);
    note.setAttributes(noteAttributes);
    return note;
  }
}
