package com.larseckart.movietracker;

class DuplicateMovieException extends RuntimeException {

  public DuplicateMovieException(String name) {
    super(name);
  }
}
