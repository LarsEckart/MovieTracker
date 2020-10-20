package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import org.junit.jupiter.api.Test;

class TestRating {

  @Test
  void anonymous_rating() throws Exception {
    Movie starWars = new Movie("Star Wars", Category.SCIFI);
    starWars.addRating(new Rating(5));

    Iterator<Rating> ratings = starWars.ratings();
    assertThat(ratings.hasNext()).isTrue();
    Rating theRating = ratings.next();
    assertThat(theRating.value()).isEqualTo(5);
    assertThat(theRating.source()).isEqualTo("Anonymous");
  }

  @Test
  void rating_with_source() throws Exception {
    Movie starWars = new Movie("Star Wars", Category.SCIFI);
    starWars.addRating(new Rating(5, "New York Times"));

    Iterator<Rating> ratings = starWars.ratings();
    assertThat(ratings.hasNext()).isTrue();
    Rating theRating = ratings.next();
    assertThat(theRating.value()).isEqualTo(5);
    assertThat(theRating.source()).isEqualTo("New York Times");
  }
}
