package com.larseckart.movietracker;

import java.util.Vector;

public class MovieListEditor {

  private final MovieListEditorView aView;
  private final MovieList movies;

  private MovieList filtered;
  private Movie selectedMovie;

  public MovieListEditor(MovieList movieList, MovieListEditorView aView) {
    this.movies = movieList;
    this.filtered = movies;
    this.aView = aView;
    aView.setMovies(new Vector<>(movieList.getMovies()));
    aView.setEditor(this);
  }

  public void add() {
    String newName = aView.getNameField();
    try {
      Movie newMovie = new Movie(newName);
      // TODO: rating and category ignored right now. update add related test(s) and make them green
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
      aView.setCategoryField(selectedMovie.getCategory());

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
        updateMovieList();
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
    selectedMovie.setCategory(aView.getCategoryField());
  }

  private void updateMovieList() {
    aView.setMovies(new Vector<>(filtered.getMovies()));
  }

  public void filterOnCategory(Category category) {
    filtered = movies.categorySublist(category);
    updateMovieList();
  }
}
