package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import org.junit.jupiter.api.Test;

class TestRating {

  @Test
  void anonymous_rating() {
    Movie starWars = new Movie("Star Wars", Category.SCIFI);
    starWars.addRating(new Rating(5));

    Iterator<Rating> ratings = starWars.ratings();
    assertThat(ratings.hasNext()).isTrue();
    Rating theRating = ratings.next();
    assertThat(theRating.value()).isEqualTo(5);
    assertThat(theRating.source()).isEqualTo("Anonymous");
  }

  @Test
  void rating_with_source() {
    Movie starWars = new Movie("Star Wars", Category.SCIFI);
    starWars.addRating(new Rating(5, "New York Times"));

    Iterator<Rating> ratings = starWars.ratings();
    assertThat(ratings.hasNext()).isTrue();
    Rating theRating = ratings.next();
    assertThat(theRating.value()).isEqualTo(5);
    assertThat(theRating.source()).isEqualTo("New York Times");
  }

  @Test
  void noReview() {
    Rating aRating = new Rating(3, "Dave");
    assertThat(aRating.review()).isEqualTo("");
  }

  @Test
  void withConstructedReview() {
    Rating aRating = new Rating(3, "Dave", "Test Review");
    assertThat(aRating.review()).isEqualTo("Test Review");
  }
}
