package com.larseckart.movietracker;

import java.util.Vector;

public interface MovieListEditorView {

  void setMovies(Vector<Movie> movies);

  void setName(String name);

  String getNewName();

  void setEditor(MovieListEditor anEditor);

  void duplicateException(String name);
}
