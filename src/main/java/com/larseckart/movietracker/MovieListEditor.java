package com.larseckart.movietracker;

import java.util.Vector;

public class MovieListEditor {

  private final MovieList movies;
  private final MovieListEditorView aView;

  private Movie selectedMovie;

  public MovieListEditor(MovieList movieList, MovieListEditorView aView) {
    this.movies = movieList;
    this.aView = aView;
    aView.setMovies(new Vector<>(movieList.getMovies()));
    aView.setEditor(this);
  }

  public void add() {
    String newName = aView.getNameField();
    try {
      Movie newMovie = new Movie(newName);
      movies.add(newMovie);
      updateMovieList();
    } catch (DuplicateMovieException e) {
      aView.duplicateException(newName);
    }
  }

  public void select(int index) {
    if (index == -1) {
      selectedMovie = null;
    } else {
      selectedMovie = movies.getMovie(index);
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
      if (selectedMovie.getName().equals(newName)) {
        updateMovieRating();
      } else {
        try {
          movies.rename(selectedMovie, newName);
          updateMovieRating();
          updateMovieList();
        } catch (DuplicateMovieException e) {
          aView.duplicateException(newName);
        }
      }
    }
  }

  private void updateMovieRating() {
    selectedMovie.setRating(aView.getRatingField() - 1);
  }

  private void updateMovieList() {
    aView.setMovies(new Vector<>(movies.getMovies()));
  }
}
