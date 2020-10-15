package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPersistence {

  private StringWriter destination;
  private MovieList movieList;

  @BeforeEach
  void setUp() {
    destination = new StringWriter();
    movieList = new MovieList();
  }

  @Test
  void writingEmptyList() throws Exception {
    movieList.writeTo(destination);
    assertThat(destination.toString()).isEqualTo("");
  }

  @Test
  void writing_one_movie() throws Exception {
    movieList.add(new Movie("Star Wars", Category.SCIFI, 4));
    movieList.writeTo(destination);
    assertThat(destination.toString()).isEqualTo("Star Wars|Science Fiction|4\n");
  }

  @Test
  void writing_multiple_movie() throws Exception {
    movieList.add(new Movie("Star Wars", Category.SCIFI, 4));
    movieList.add(new Movie("Finding Nemo", Category.KIDS, 5));
    movieList.writeTo(destination);
    assertThat(destination.toString())
        .isEqualTo("Star Wars|Science Fiction|4\nFinding Nemo|Kids|5\n");
  }


  @Test
  void test_saving() throws Exception {
    movieList.add(new Movie("Star Wars", Category.SCIFI, 4));
    movieList.add(new Movie("Finding Nemo", Category.KIDS, 5));
    String expected = "Star Wars|Science Fiction|4\nFinding Nemo|Kids|5\n";
    File outputFile = File.createTempFile("testSaveAs", ".dat");
    outputFile.deleteOnExit();

    MovieListEditorView mockView = mock(MovieListEditorView.class);
    given(mockView.getFile("*.dat")).willReturn(outputFile);
    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.saveAs();
    assertThat(Files.readString(outputFile.toPath())).isEqualTo(expected);
  }
}
