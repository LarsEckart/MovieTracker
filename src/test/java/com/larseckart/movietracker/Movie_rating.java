package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class Movie_rating {

  @Test
  void unrated() {
    Movie starWars = new Movie("Star Wars");
    assertThat(starWars.hasRating()).isFalse();
  }

  @Test
  void rated() {
    Movie lotr = new Movie("Lord of the Rings", 5);
    assertThat(lotr.hasRating()).isTrue();
    assertThat(lotr.getRating()).isEqualTo(5);
  }

  @Test
  void unrated_exception() {
    Movie starWars = new Movie("Star Wars");
    assertThrows(UnratedException.class, starWars::getRating);
  }
}
