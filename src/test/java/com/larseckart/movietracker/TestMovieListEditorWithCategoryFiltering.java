package com.larseckart.movietracker;

import static org.mockito.Mockito.*;

import java.util.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMovieListEditorWithCategoryFiltering {

  private MovieListEditorView mockView;
  private Movie starWars;
  private Movie starTrek;
  private Movie starGate;
  private Movie theShining;
  private Movie carrie;
  private Movie fotr;
  private Movie redOctober;
  private Movie congo;
  private Movie princessBride;

  private MovieList movieList;
  private MovieList fantasyList;
  private MovieList scifiList;
  private MovieList thrillerList;
  private MovieList horrorList;

  private Vector<Movie> movies;
  private Vector<Movie> fantasyMovies;
  private Vector<Movie> horrorMovies;
  private Vector<Movie> thrillerMovies;
  private Vector<Movie> scifiMovies;

  @BeforeEach
  void setUp() {
    starWars = new Movie("Star Wars", Category.SCIFI, 4);
    starTrek = new Movie("Star Trek", Category.SCIFI, 3);
    starGate = new Movie("StarGate", Category.SCIFI, 5);
    theShining = new Movie("The Shining", Category.HORROR, 5);
    fotr = new Movie("Fellowship of the Ring", Category.FANTASY, 5);
    carrie = new Movie("Carrie", Category.HORROR, 1);
    redOctober = new Movie("The Hunt for Red October", Category.THRILLER, 5);
    congo = new Movie("Congo", Category.THRILLER, 5);
    princessBride = new Movie("Princess Bride", Category.FANTASY, 3);

    movieList = new MovieList();
    movieList.add(starWars);
    movieList.add(starTrek);
    movieList.add(starGate);
    movieList.add(theShining);
    movieList.add(fotr);
    movieList.add(carrie);
    movieList.add(redOctober);
    movieList.add(congo);
    movieList.add(princessBride);

    movies = new Vector<>(movieList.getMovies());

    scifiList = new MovieList();
    scifiList.add(starWars);
    scifiList.add(starTrek);
    scifiList.add(starGate);
    scifiMovies = new Vector<>(scifiList.getMovies());

    thrillerList = new MovieList();
    thrillerList.add(redOctober);
    thrillerList.add(congo);
    thrillerMovies = new Vector<>(thrillerList.getMovies());

    horrorList = new MovieList();
    horrorList.add(theShining);
    horrorList.add(carrie);
    horrorMovies = new Vector<>(horrorList.getMovies());

    fantasyList = new MovieList();
    fantasyList.add(fotr);
    fantasyList.add(princessBride);
    fantasyMovies = new Vector<>(fantasyList.getMovies());

    mockView = mock(MovieListEditorView.class);
  }

  @Test
  void category_filtering() throws Exception {
    MovieListEditor editor = new MovieListEditor(movieList, mockView);

    editor.filterOnCategory(Category.SCIFI);
    editor.filterOnCategory(Category.ALL);

    verify(mockView, times(2)).setMovies(movies);
    verify(mockView).setMovies(scifiMovies);
  }
}
