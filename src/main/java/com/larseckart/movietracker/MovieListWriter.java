package com.larseckart.movietracker;

import java.io.IOException;

public interface MovieListWriter {

  void write(MovieList movies) throws IOException;
}
