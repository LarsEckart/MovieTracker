package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Movie_should {

  private Movie movie;

  @BeforeEach
  void setUp() {
    movie = new Movie("Star Wars");
  }

  @Test
  void have_a_name() {
    assertThat(movie.getName()).isEqualTo("Star Wars");
  }

  @Test
  void display_movie_name_for_toString() {
    assertThat(movie.toString()).isEqualTo("Star Wars");
  }

  @Test
  void renaming() {
    String newMovie = "Star Trek";
    Movie aMovie = new Movie("Star Wars");

    aMovie.rename(newMovie);

    assertThat(aMovie.getName()).isEqualTo(newMovie);
  }

  @Test
  void null_name() {
    String nullString = null;
    assertThrows(IllegalArgumentException.class, () -> new Movie(nullString));
  }

  @Test
  void empty_name() {
    assertThrows(IllegalArgumentException.class, () -> new Movie(""));
  }

  @Test
  void rename_null_name() {
    Movie aMovie = new Movie("Star Wars");

    assertThrows(IllegalArgumentException.class, () -> aMovie.rename(null));
  }

  @Test
  void rename_empty_name() {
    Movie aMovie = new Movie("Star Wars");

    assertThrows(IllegalArgumentException.class, () -> aMovie.rename(""));
  }

  @Test
  void copy_constructor() {
    Movie starWars = new Movie("Star Wars");
    Movie copyOfStarWars = new Movie(starWars);
    assertThat(copyOfStarWars).isNotSameAs(starWars);
    assertThat(copyOfStarWars).isEqualTo(starWars);
  }

  @Test
  void uncategorized_movie() {
    assertThat(movie.getCategory()).isEqualTo(Category.UNCATEGORIZED);
  }

  @Test
  void science_fiction_category_movie() {
    Movie alien = new Movie("Alien", Category.SCIFI);
    assertThat(alien.getCategory()).isEqualTo(Category.SCIFI);
  }
}
