package com.larseckart.movietracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class PlainTextMovieListReader implements MovieListReader {

  public PlainTextMovieListReader() {
  }

  @Override
  public MovieList read(Reader reader) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(reader);
    MovieList movieList = new MovieList();

    //bufferedReader.lines().map(line -> Movie.readFrom(line)).forEach(movieList::add);

    for (Movie newMovie = Movie.readFrom(bufferedReader); newMovie != null;
        newMovie = Movie.readFrom(bufferedReader)) {
      movieList.add(newMovie);
    }
    return movieList;
  }
}
