package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMovieListWithThreeMovies {

  private MovieList movieList;
  private Movie starWars;
  private Movie starTrek;
  private Movie starGate;

  @BeforeEach
  void setUp() {
    movieList = new MovieList();
    starWars = new Movie("Star Wars");
    starTrek = new Movie("Star Trek");
    starGate = new Movie("Stargate");

    movieList.add(starWars);
    movieList.add(starTrek);
    movieList.add(starGate);
  }

  @Test
  void adding_a_duplicate() {
    assertThrows(DuplicateMovieException.class, () -> movieList.add(starGate));
  }

  @Test
  void renaming() {
    final String newName = "Star Wars I";
    movieList.rename(starWars, newName);
  }

  @Test
  void renaming_duplicate() {
    assertAll(
        () -> assertThrows(DuplicateMovieException.class,
            () -> movieList.rename(starTrek, "Star Wars")),
        () -> assertThat(movieList.size()).withFailMessage("failed rename shouldn't change size")
            .isEqualTo(3),
        () -> assertThat(starTrek.getName())
            .withFailMessage("failed rename shouldn't change the name").isEqualTo("Star Trek"));
  }
}
