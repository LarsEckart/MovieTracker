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
}
