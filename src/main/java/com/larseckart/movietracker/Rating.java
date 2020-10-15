package com.larseckart.movietracker;

record Rating(int value, String source) {

  public Rating(int rating) {
    this(rating, "Anonymous");
  }
  public Rating(int value, String source) {
    this.value = value;
    this.source = source;
  }
}
