package com.larseckart.movietracker;

import java.util.Vector;

public interface MovieListEditorView {

  void setMovies(Vector<Movie> movies);

  void setNameField(String name);

  void setRatingField(int rating);

  String getNameField();

  int getRatingField();

  void setEditor(MovieListEditor anEditor);

  void duplicateException(String name);
}
