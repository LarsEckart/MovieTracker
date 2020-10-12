package com.larseckart.movietracker;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGui {

  private MovieListEditorView mockView;
  private Vector<Movie> movies;
  private Movie starWars;
  private Movie starTrek;
  private Movie starGate;
  private MovieList movieList;

  @BeforeEach
  void setUp() {
    starWars = new Movie("Star Wars");
    starTrek = new Movie("Star Trek");
    starGate = new Movie("Stargate");

    movies = new Vector<>();
    movies.add(starWars);
    movies.add(starTrek);
    movies.add(starGate);

    movieList = new MovieList();
    movieList.add(starWars);
    movieList.add(starTrek);
    movieList.add(starGate);
    mockView = mock(MovieListEditorView.class);
  }

  @Test
  void test_list() {
    new MovieListEditor(movieList, mockView);
    verify(mockView).setMovies(movies);
  }

  @Test
  void adding() {
    String LOST_IN_SPACE = "Lost in Space";
    Movie lostInSpace = new Movie(LOST_IN_SPACE);
    Vector<Movie> moviesWithAddition = new Vector<>(movies);
    moviesWithAddition.add(lostInSpace);

    given(mockView.getNewName()).willReturn(LOST_IN_SPACE);

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.add();

    assertAll(
        () -> verify(mockView).setMovies(movies),
        () -> verify(mockView).getNewName(),
        () -> verify(mockView).setMovies(moviesWithAddition));
  }

  @Test
  void selecting() throws Exception {
    mockView.setMovies(movies);

    MovieListEditor editor = new MovieListEditor(movieList, mockView);

    editor.select(1);

    verify(mockView).setName("Star Trek");
  }

  @Test
  void selecting_another() {
    mockView.setMovies(movies);

    MovieListEditor editor = new MovieListEditor(movieList, mockView);

    editor.select(0);

    verify(mockView).setName("Star Wars");
  }

  @Test
  void updating() {
    Vector<Movie> newMovies = new Vector<>();
    newMovies.add(starWars);
    newMovies.add(new Movie("Star Trek I"));
    newMovies.add(starGate);

    given(mockView.getNewName()).willReturn("Star Trek I");

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.select(1);
    editor.update();

    verify(mockView).setName("Star Trek");
    verify(mockView).getNewName();
    verify(mockView).setMovies(newMovies);
  }

  @Test
  void adding_duplicate() {
    String LOST_IN_SPACE = "Lost in Space";
    Movie lostInSpace = new Movie(LOST_IN_SPACE);
    Vector<Movie> moviesWithAddition = new Vector<>(movies);
    moviesWithAddition.add(lostInSpace);

    given(mockView.getNewName()).willReturn("Star Wars");

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.add();

    verify(mockView).duplicateException("Star Wars");
  }

  @Test
  void rename_duplicate() {
    given(mockView.getNewName()).willReturn("Star Wars");

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.select(1);
    editor.update();

    verify(mockView).duplicateException("Star Wars");
  }
}
