package com.larseckart.movietracker;

import java.util.Objects;

public class Movie {

  private String name;
  private int totalRating;
  private int numberOfRatings;

  public Movie(String name) {
    checkNull(name);
    checkEmpty(name);
    this.name = name;
  }

  private void checkEmpty(String name) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("empty Movie name");
    }
  }

  public String getName() {
    return this.name;
  }

  public void addRating(int rating) {
    totalRating += rating;
    numberOfRatings++;
  }

  public int getAverageRating() {
    return totalRating / numberOfRatings;
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Movie movie = (Movie) o;
    return Objects.equals(name, movie.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  public void rename(String newName) {
    checkNull(newName);
    checkEmpty(newName);
    name = newName;
  }

  private void checkNull(String newName) {
    if (newName == null) {
      throw new IllegalArgumentException("null Movie name");
    }
  }
}
