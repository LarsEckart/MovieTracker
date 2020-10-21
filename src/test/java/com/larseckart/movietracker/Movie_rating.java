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
  void rated() throws UnratedException {
    Movie lotr = new Movie("Lord of the Rings", 5);
    assertThat(lotr.hasRating()).isTrue();
    assertThat(lotr.getAverageRating()).isEqualTo(5);
  }

  @Test
  void unrated_exception() {
    Movie starWars = new Movie("Star Wars");
    assertThrows(UnratedException.class, starWars::getAverageRating);
  }

  @Test
  void adding_one_rating() throws Exception {
    Movie starTrek = new Movie("Star Trek", Category.SCIFI);
    starTrek.addRating(new Rating(3));
    assertThat(starTrek.getAverageRating()).isEqualTo(3);
  }

  @Test
  void adding_multiple_rating() throws Exception {
    Movie starTrek = new Movie("Star Trek", Category.SCIFI);
    starTrek.addRating(new Rating(3));
    starTrek.addRating(new Rating(5));
    starTrek.addRating(new Rating(5));
    starTrek.addRating(new Rating(3));
    assertThat(starTrek.getAverageRating()).isEqualTo(4);
  }
}
