package com.larseckart.movietracker;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MovieListEditor {

  private final MovieListReader movieListReader = new XMLMovieListReader();
  private final MovieListWriter movieListWriter = new XMLMovieListWriter();
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
    aView.setMovies(new ArrayList<>(movieList.getMovies()));
    aView.setEditor(this);
  }

  public void add() {
    String newName = aView.getNameField();
    try {
      var movie = new Movie(newName, aView.getCategoryField());
      movies.add(movie);
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
      aView.setRatings(selectedMovie.getRatings());
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
    selectedMovie.setCategory(aView.getCategoryField());
    filterOnCategory(filterCategory);
  }

  private void updateMovieList() {
    aView.setMovies(new ArrayList<>(filteredMovies.getMovies()));
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
      movieListWriter.write(writer, movies);
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

    return load(inputFile);
  }

  public boolean load(File file) throws IOException {
    var reader = new FileReader(file, StandardCharsets.UTF_8);
    movies = movieListReader.read(reader);
    filterOnCategory(Category.ALL);
    outputFile = file;
    return true;
  }

  public void addRating() {
    if (selectedMovie != null) {
      int value = aView.getRatingValueField();
      String source = aView.getRatingSourceField();
      String review = aView.getRatingReviewField();
      selectedMovie.addRating(new Rating(value, source, review));
      aView.setRatings(selectedMovie.getRatings());
    }
  }

  public void selectRating(int index) {
    if (index < 0) {
      return;
    }
    Rating selectedRating = selectedMovie.getRating(index);
    aView.setRatingReviewField(selectedRating.review());
    aView.setRatingSourceField(selectedRating.source());
    aView.setRatingValueField(selectedRating.value());
  }
}
