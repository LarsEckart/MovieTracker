package com.larseckart.movietracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Movie {

  private Category category;
  private String name;
  private List<Rating> ratings;

  public Movie(String name) {
    this(name, -1);
  }

  public Movie(String name, int rating) {
    this(name, Category.UNCATEGORIZED, rating);
  }

  public Movie(Movie original) {
    this.name = original.name;
    this.category = original.category;
    this.ratings = new ArrayList<>(original.ratings);
  }

  public Movie(String name, Category category) {
    this(name, category, -1);
  }

  public Movie(String name, Category category, int rating) {
    checkNull(name);
    checkEmpty(name);
    this.name = name;
    this.category = (category != null) ? category : Category.UNCATEGORIZED;
    ratings = new ArrayList<>();
    if (rating >= 0) {
      ratings.add(new Rating(rating));
    }
  }

  public Movie(String name, Category category, int rating, int count) {
    this(name, category, rating);
    ratings = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      ratings.add(new Rating(rating / count));
    }
  }

  public static Movie readFrom(BufferedReader reader) throws IOException {
    String oneLine = reader.readLine();
    if (oneLine == null) {
      return null;
    }

    StringTokenizer tokenizer = new StringTokenizer(oneLine, "|");
    try {
      String name = tokenizer.nextToken();
      Category category = Category.getCategoryNamed(tokenizer.nextToken());
      int rating = Integer.parseInt(tokenizer.nextToken());
      int count = Integer.parseInt(tokenizer.nextToken());
      return new Movie(name, category, rating, count);
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
    addRating(new Rating(aRating));
  }

  public void addRating(int aRating, String source) {
    addRating(new Rating(aRating, source));
  }

  public void addRating(Rating rating) {
    ratings.add(rating);
  }

  public List<Rating> getRatings() {
    return ratings;
  }

  public void setRating(int rating) {
    this.ratings = new ArrayList<>();
    this.ratings.add(new Rating(rating));
  }

  public int getRating() throws UnratedException {
    if (hasRating()) {
      return ratings.stream().mapToInt(Rating::value).sum() / ratings.size();
    } else {
      throw new UnratedException();
    }
  }

  public boolean hasRating() {
    return !ratings.isEmpty();
  }

  public Iterator<Rating> ratings() {
    return ratings.iterator();
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

}
