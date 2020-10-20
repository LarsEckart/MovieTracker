package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLogicalLayer {

  private MovieListEditorView mockView;

  private Vector<Movie> movies;
  private Movie starWars;
  private Movie starTrek;
  private Movie starGate;
  private MovieList movieList;
  private Movie theShining;

  @BeforeEach
  void setUp() {
    starWars = new Movie("Star Wars", Category.SCIFI, 5);
    starTrek = new Movie("Star Trek", Category.SCIFI, 3);
    starGate = new Movie("Stargate", Category.SCIFI);
    theShining = new Movie("The Shining", Category.HORROR, 2);

    movies = new Vector<>();
    movies.add(starWars);
    movies.add(starTrek);
    movies.add(starGate);
    movies.add(theShining);

    movieList = new MovieList();
    movieList.add(starWars);
    movieList.add(starTrek);
    movieList.add(starGate);
    movieList.add(theShining);
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
    editor.select(1);
    editor.select(2);
    editor.select(3);

    verify(mockView).setNameField("Star Wars");
    verify(mockView).setRatingField(6);

    verify(mockView).setNameField("Star Trek");
    verify(mockView).setRatingField(4);

    verify(mockView).setNameField("Stargate");
    verify(mockView).setRatingField(0);

    verify(mockView, times(3)).setCategoryField(Category.SCIFI);

    verify(mockView).setNameField("The Shining");
    verify(mockView).setRatingField(3);
    verify(mockView).setCategoryField(Category.HORROR);
  }

  @Test
  void selecting_another() {
    mockView.setMovies(movies);

    MovieListEditor editor = new MovieListEditor(movieList, mockView);

    editor.select(0);

    verify(mockView).setNameField("Star Wars");
  }

  @Test
  void updating() throws UnratedException {
    given(mockView.getNameField()).willReturn("Star Trek I");
    given(mockView.getRatingField()).willReturn(6);
    given(mockView.getCategoryField()).willReturn(Category.COMEDY);

    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.select(1);
    editor.update();

    verify(mockView).setNameField("Star Trek");
    verify(mockView).setRatingField(4);
    verify(mockView).setCategoryField(Category.SCIFI);

    verify(mockView).getNameField();
    verify(mockView).getRatingField();
    verify(mockView).getCategoryField();
    // mockito doesnt understand the difference in list contents, we call it once with movies and
    // once with updated Movies but for mockito it's the same call (since objects are the same,
    // but changed values)
    verify(mockView, times(3)).setMovies(movies);
    assertThat(starTrek.getRating()).isEqualTo(5);
    assertThat(starTrek.getCategory()).isEqualTo(Category.COMEDY);
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

  @Test
  void add_rating_to_movie() throws Exception {
    MovieListEditor editor = new MovieListEditor(movieList, mockView);
    editor.select(0);
    given(mockView.getRatingSourceField()).willReturn("Dave");
    given(mockView.getRatingValueField()).willReturn(2);

    editor.addRating();

    verify(mockView).getRatingValueField();
    verify(mockView).getRatingSourceField();
    // mockito doesnt understand the difference in list contents, we call it once with movies and
    // once with updated Movies but for mockito it's the same call (since objects are the same,
    // but changed values)
    verify(mockView, times(2)).setRatings(List.of(new Rating(5), new Rating(2, "Dave")));
  }
}
