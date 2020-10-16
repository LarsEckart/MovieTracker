package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Reader;

public interface MovieListReader {

  MovieList getMovies(Reader reader) throws IOException;
}
