package com.larseckart.movietracker;

import java.util.Vector;

public class MovieListEditor {

  private final MovieList movieList;
  private final MovieListEditorView aView;

  private Movie selectedMovie;

  public MovieListEditor(MovieList movieList, MovieListEditorView aView) {
    this.movieList = movieList;
    this.aView = aView;
    aView.setMovies(new Vector<>(movieList.getMovies()));
    aView.setEditor(this);
  }

  public void add() {
    String newName = aView.getNameField();
    try {
      Movie newMovie = new Movie(newName);
      movieList.add(newMovie);
      updateMovieList();
    } catch (DuplicateMovieException e) {
      aView.duplicateException(newName);
    }
  }

  public void select(int index) {
    if (index == -1) {
      selectedMovie = null;
    } else {
      selectedMovie = movieList.getMovie(index);
      aView.setNameField(selectedMovie.getName());

      try {
        aView.setRatingField(selectedMovie.getRating() + 1);
      } catch (UnratedException e) {
        aView.setRatingField(0);
      }
    }
  }

  public void update() {
    if (selectedMovie != null) {
      String newName = aView.getNameField();
      try {
        movieList.rename(selectedMovie, newName);
      } catch (DuplicateMovieException e) {
        aView.duplicateException(newName);
      }
    }
  }

  private void updateMovieList() {
    aView.setMovies(new Vector<>(movieList.getMovies()));
  }
}
