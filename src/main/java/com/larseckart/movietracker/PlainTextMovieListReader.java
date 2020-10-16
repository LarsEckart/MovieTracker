package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Reader;

public class PlainTextMovieListReader implements MovieListReader {

  public PlainTextMovieListReader() {
  }

  @Override
  public MovieList read(Reader reader) throws IOException {
    return MovieList.readFrom(reader);
  }
}
