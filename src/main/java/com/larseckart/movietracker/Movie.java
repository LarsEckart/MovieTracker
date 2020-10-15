package com.larseckart.movietracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;
import java.util.StringTokenizer;

public class Movie {

  public static final String DELIMITER = "|";
  private Category category;
  private String name;
  private int rating;
  private int numberOfRatings;

  public Movie(String name) {
    this(name, -1);
  }

  public Movie(String name, int rating) {
    this(name, Category.UNCATEGORIZED, rating);
  }

  public Movie(Movie movie) {
    this.name = movie.name;
    this.rating = movie.rating;
    this.numberOfRatings = movie.numberOfRatings;
    this.category = movie.category;
  }

  public Movie(String name, Category category) {
    this(name, category, -1);
  }

  public Movie(String name, Category category, int rating) {
    checkNull(name);
    checkEmpty(name);
    this.name = name;
    this.category = (category != null) ? category : Category.UNCATEGORIZED;
    if (rating >= 0) {
      this.rating = rating;
      numberOfRatings++;
    }
  }

  public static Movie readFrom(BufferedReader reader) throws IOException {
    String oneLine = reader.readLine();
    if (oneLine == null) {
      return null;
    }

    StringTokenizer tokenizer = new StringTokenizer(oneLine, DELIMITER);
    try {
      String name = tokenizer.nextToken();
      Category category = Category.getCategoryNamed(tokenizer.nextToken());
      int rating = Integer.parseInt(tokenizer.nextToken());
      return new Movie(name, category, rating);
    } catch (NumberFormatException e) {
      throw new IOException("Badly formatted movie collection");
    }
  }

  private void checkEmpty(String name) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("empty Movie name");
    }
  }

  public String getName() {
    return this.name;
  }

  public void addRating(int aRating) {
    if (aRating == -1) {
      this.rating = aRating;
    } else {
      this.rating += aRating;
    }
    numberOfRatings++;
  }

  public void setRating(int rating) {
    this.rating = rating;
    numberOfRatings = 1;
  }

  public int getRating() throws UnratedException {
    if (hasRating()) {
      return rating / numberOfRatings;
    } else {
      throw new UnratedException();
    }
  }

  public boolean hasRating() {
    return numberOfRatings > 0;
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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  void
  writeMovie(Writer destination) throws IOException {
    destination.write(getName());
    writeSeparator(destination);
    destination.write(getCategory().toString());
    writeSeparator(destination);
    try {
      destination.write(Integer.toString(getRating()));
    } catch (UnratedException e) {
      destination.write("-1");
    }
    destination.write("\n");
  }

  private void writeSeparator(Writer destination) throws IOException {
    destination.write(DELIMITER);
  }
}
