package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Reader;

class XMLMovieListReader implements MovieListReader {

  @Override
  public MovieList getMovies(Reader reader) throws IOException {
    return new MovieList();
  }
}
