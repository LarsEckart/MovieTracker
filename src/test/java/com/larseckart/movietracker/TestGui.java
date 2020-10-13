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
    starWars = new Movie("Star Wars", 5);
    starTrek = new Movie("Star Trek", 3);
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

    given(mockView.getNameField()).willReturn(LOST_IN_SPACE);

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.add();

    assertAll(
        () -> verify(mockView).setMovies(movies),
        () -> verify(mockView).getNameField(),
        () -> verify(mockView).setMovies(moviesWithAddition));
  }

  @Test
  void selecting() throws Exception {
    mockView.setMovies(movies);

    MovieListEditor editor = new MovieListEditor(movieList, mockView);

    editor.select(0);
    verify(mockView).setNameField("Star Wars");
    verify(mockView).setRatingField(6);

    editor.select(1);
    verify(mockView).setNameField("Star Trek");
    verify(mockView).setRatingField(4);

    editor.select(2);
    verify(mockView).setNameField("Stargate");
    verify(mockView).setRatingField(0);
  }

  @Test
  void selecting_another() {
    mockView.setMovies(movies);

    MovieListEditor editor = new MovieListEditor(movieList, mockView);

    editor.select(0);

    verify(mockView).setNameField("Star Wars");
  }

  @Test
  void updating() {
    Vector<Movie> newMovies = new Vector<>();
    newMovies.add(starWars);
    newMovies.add(new Movie("Star Trek I", 5));
    newMovies.add(starGate);

    given(mockView.getNameField()).willReturn("Star Trek I");
    given(mockView.getRatingField()).willReturn(6);

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.select(1);
    editor.update();

    verify(mockView).setNameField("Star Trek");
    verify(mockView).setRatingField(4);
    verify(mockView).getNameField();
    verify(mockView).getRatingField();
    // mockito doesnt understand the difference in list contents, we call it once with movies and once with newMovies
    // but for mockito it's the same call (since objects are the same, but changed values)
    //verify(mockView).setMovies(newMovies);
  }

  @Test
  void adding_duplicate() {
    String LOST_IN_SPACE = "Lost in Space";
    Movie lostInSpace = new Movie(LOST_IN_SPACE);
    Vector<Movie> moviesWithAddition = new Vector<>(movies);
    moviesWithAddition.add(lostInSpace);

    given(mockView.getNameField()).willReturn("Star Wars");

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.add();

    verify(mockView).duplicateException("Star Wars");
  }

  @Test
  void rename_duplicate() {
    given(mockView.getNameField()).willReturn("Star Wars");

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.select(1);
    editor.update();

    verify(mockView).duplicateException("Star Wars");
  }
}
