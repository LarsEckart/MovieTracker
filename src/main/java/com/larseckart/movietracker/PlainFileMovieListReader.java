package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Reader;

public class PlainFileMovieListReader implements MovieListReader {

  public PlainFileMovieListReader() {
  }

  @Override
  public MovieList getMovies(Reader reader) throws IOException {
    return MovieList.readFrom(reader);
  }
}
