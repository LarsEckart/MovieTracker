package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMovieListWithOneMovie {

  private MovieList movieList;
  private Movie starWars;

  @BeforeEach
  void setUp() {
    movieList = new MovieList();
    starWars = new Movie("Star Wars");

    movieList.add(starWars);
  }

  @Test
  void list_has_size_of_one_after_adding_one_movie() {
    assertThat(movieList.size()).isEqualTo(1);
  }
}
