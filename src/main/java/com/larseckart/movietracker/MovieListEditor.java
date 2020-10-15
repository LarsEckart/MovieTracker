package com.larseckart.movietracker;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class MovieListEditor {

  private final MovieListEditorView aView;
  private MovieList movies;

  private MovieList filteredMovies;
  private Movie selectedMovie;
  private Category filterCategory = Category.ALL;
  private File outputFile;

  public MovieListEditor(MovieList movieList, MovieListEditorView aView) {
    this.movies = movieList;
    this.filteredMovies = movies;
    this.aView = aView;
    aView.setMovies(new Vector<>(movieList.getMovies()));
    aView.setEditor(this);
  }

  public void add() {
    String newName = aView.getNameField();
    try {
      Movie newMovie = new Movie(newName, aView.getCategoryField(), aView.getRatingField() - 1);
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
      selectedMovie = filteredMovies.getMovie(index);
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
    filterOnCategory(filterCategory);
  }

  private void updateMovieList() {
    aView.setMovies(new Vector<>(filteredMovies.getMovies()));
  }

  public void filterOnCategory(Category category) {
    filterCategory = category;
    filteredMovies = movies.categorySublist(category);
    updateMovieList();
  }

  public boolean save() throws IOException {
    if (outputFile == null) {
      return false;
    }
    try (FileWriter writer = new FileWriter(outputFile, StandardCharsets.UTF_8)) {
      movies.writeTo(writer);
      return true;
    }
  }

  public boolean saveAs() throws IOException {
    outputFile = aView.getFile("*.dat");
    return save();
  }

  public boolean load() throws IOException {
    File inputFile = aView.getFileToLoad();
    if (inputFile == null) {
      return false;
    }

    FileReader reader = new FileReader(inputFile, StandardCharsets.UTF_8);
    movies = MovieList.readFrom(reader);
    filterOnCategory(Category.ALL);
    return true;
  }
}
