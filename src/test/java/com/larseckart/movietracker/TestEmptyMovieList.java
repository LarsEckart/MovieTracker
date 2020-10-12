package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestEmptyMovieList {

  private MovieList movieList;

  @BeforeEach
  void setUp() {
    movieList = new MovieList();
  }

  @Test
  void empty_list_size_should_be_zero() {
    assertThat(movieList.size()).isEqualTo(0);
  }
}
