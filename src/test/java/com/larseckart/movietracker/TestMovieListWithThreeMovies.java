package com.larseckart.movietracker;

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
}
