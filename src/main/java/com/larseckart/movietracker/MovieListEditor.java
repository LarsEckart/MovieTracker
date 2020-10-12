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
    Movie newMovie = new Movie(aView.getNewName());
    movieList.add(newMovie);
    updateMovieList();
  }

  public void select(int index) {
    if (index == -1) {
      selectedMovie = null;
    } else {
      selectedMovie = movieList.getMovie(index);
      aView.setName(selectedMovie.getName());
    }
  }

  public void update() {
    if (selectedMovie != null) {
      selectedMovie.rename(aView.getNewName());
      updateMovieList();
    }
  }

  private void updateMovieList() {
    aView.setMovies(new Vector<>(movieList.getMovies()));
  }
}
