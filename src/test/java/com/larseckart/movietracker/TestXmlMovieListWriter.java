package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestXmlMovieListWriter {
  private StringWriter destination;
  private MovieList movieList;

  @BeforeEach
  void setUp() {
    destination = new StringWriter();
    movieList = new MovieList();
  }
  @Test
  void writing_empty_list() throws Exception {
    MovieListWriter writer = new XMLMovieListWriter(destination);

    writer.write(movieList);

    assertThat(destination.toString()).isEqualTo("<movielist></movielist>");
  }
}
