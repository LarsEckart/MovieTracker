package com.larseckart.movietracker;

import java.util.Objects;

record Rating(int value, String source) {

  public Rating(int rating) {
    this(rating, "Anonymous");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rating rating = (Rating) o;
    return value == rating.value &&
           Objects.equals(source, rating.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, source);
  }
}
