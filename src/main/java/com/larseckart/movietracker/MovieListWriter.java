package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Writer;

public interface MovieListWriter {

  void write(Writer destination, MovieList movies) throws IOException;
}
