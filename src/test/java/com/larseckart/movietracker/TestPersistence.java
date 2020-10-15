package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;
import org.junit.jupiter.api.Test;

class TestPersistence {

  @Test
  void writingEmptyList() throws Exception {
    StringWriter destination = new StringWriter();
    MovieList emptyList = new MovieList();
    emptyList.writeTo(destination);
    assertThat(destination.toString()).isEqualTo("");
  }

  @Test
  void writing_one_movie() throws Exception {
    StringWriter destination = new StringWriter();
    MovieList movieList = new MovieList();
    movieList.add(new Movie("Star Wars", Category.SCIFI, 4));
    movieList.writeTo(destination);
    assertThat(destination.toString()).isEqualTo("Star Wars|Science Fiction|4\n");
  }
}
