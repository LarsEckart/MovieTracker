package com.larseckart.movietracker;

import java.util.Objects;

public class Movie {

  private String category;
  private String name;
  private int totalRating;
  private int numberOfRatings;
  private int rating;

  public Movie(String name) {
    this(name, -1);
  }

  public Movie(String name, int rating) {
    this(name, null, rating);
  }

  public Movie(Movie movie) {
    this.name = movie.name;
    this.rating = movie.rating;
    this.category = movie.category;
  }

  public Movie(String name, String category) {
    this(name, category, -1);
  }

  public Movie(String name, String category, int rating) {
    checkNull(name);
    checkEmpty(name);
    this.name = name;
    this.category = (category != null) ? category : "Uncategorized";
    this.rating = rating;
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

  public boolean hasRating() {
    return rating >= 0;
  }

  public int getRating() throws UnratedException {
    if (hasRating()) {
      return rating;
    } else {
      throw new UnratedException();
    }
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getCategory() {
    return category;
  }
}
