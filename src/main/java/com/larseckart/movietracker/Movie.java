package com.larseckart.movietracker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;

public class Movie {

  private String name;
  private Category category;
  private List<Rating> ratings;

  public Movie(String name) {
    this(name, -1);
  }

  public Movie(String name, int rating) {
    this(name, Category.UNCATEGORIZED, rating);
  }

  public Movie(String name, Category category) {
    this(name, category, -1);
  }

  public Movie(String name, Category category, int rating) {
    checkNull(name);
    checkEmpty(name);
    this.name = name;
    this.category = Optional.ofNullable(category).orElse(Category.UNCATEGORIZED);
    ratings = new ArrayList<>();
    if (rating >= 0) {
      ratings.add(new Rating(rating));
    }
  }

  private Movie(String name, Category category, int rating, int count) {
    this(name, category, rating);
    ratings = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      ratings.add(new Rating(rating / count));
    }
  }

  public Movie(Movie original) {
    this.name = original.name;
    this.category = original.category;
    this.ratings = new ArrayList<>(original.ratings);
  }

  public static Movie readFrom(String line) {
    try {
      StringTokenizer tokenizer = new StringTokenizer(line, "|");
      return parseLine(tokenizer);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Badly formatted movie collection");
    }
  }

  private static Movie parseLine(StringTokenizer tokenizer) {
    String name = tokenizer.nextToken();
    Category category = Category.getCategoryNamed(tokenizer.nextToken());
    int rating = Integer.parseInt(tokenizer.nextToken());
    int count = Integer.parseInt(tokenizer.nextToken());
    return new Movie(name, category, rating, count);
  }

  private void checkEmpty(String aString) {
    if (aString.isEmpty()) {
      throw new IllegalArgumentException("empty Movie name");
    }
  }

  private void checkNull(String aString) {
    if (aString == null) {
      throw new IllegalArgumentException("null Movie name");
    }
  }

  public String getName() {
    return name;
  }

  public void rename(String newName) {
    checkNull(newName);
    checkEmpty(newName);
    name = newName;
  }

  public void addRating(Rating rating) {
    ratings.add(rating);
  }

  public int getRating() throws UnratedException {
    if (hasRating()) {
      return ratings.stream().mapToInt(Rating::value).sum() / ratings.size();
    } else {
      throw new UnratedException();
    }
  }

  public List<Rating> getRatings() {
    return ratings;
  }

  public Iterator<Rating> ratings() {
    return ratings.iterator();
  }

  public boolean hasRating() {
    return !ratings.isEmpty();
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
