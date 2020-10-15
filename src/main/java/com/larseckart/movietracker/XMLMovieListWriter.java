package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Writer;

class XMLMovieListWriter implements MovieListWriter {

  private final Writer destination;

  public XMLMovieListWriter(Writer destination) {
    this.destination = destination;
  }

  @Override
  public void write(MovieList movies) throws IOException {
    destination.write("<movielist></movielist>");
    destination.flush();
  }
}
