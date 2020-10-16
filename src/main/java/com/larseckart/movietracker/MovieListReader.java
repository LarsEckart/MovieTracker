package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Reader;

public interface MovieListReader {

  MovieList read(Reader reader) throws IOException;
}
