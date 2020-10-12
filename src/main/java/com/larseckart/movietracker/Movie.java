package com.larseckart.movietracker;

import java.util.Objects;

public class Movie {

  private String name;
  private int totalRating;
  private int numberOfRatings;

  public Movie(String name) {
    if (name == null) {
      throw new IllegalArgumentException("null Movie name");
    }
    if (name.isEmpty()) {
      throw new IllegalArgumentException("empty Movie name");
    }
    this.name = name;
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
    if (newName == null) {
      throw new IllegalArgumentException("null Movie name");
    }
    if (newName.isEmpty()) {
      throw new IllegalArgumentException("empty Movie name");
    }
    name = newName;
  }
}
