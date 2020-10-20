package com.larseckart.movietracker;

import java.io.File;
import java.util.List;

public interface MovieListEditorView {

  void setMovies(List<Movie> movies);

  void setNameField(String name);

  void setRatingField(int rating);

  String getNameField();

  int getRatingField();

  void setEditor(MovieListEditor anEditor);

  void duplicateException(String name);

  void setCategoryField(Category category);

  Category getCategoryField();

  File getFile(String name);

  File getFileToLoad();

  void setRatings(List<Rating> ratings);
}
