package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MovieListFilterTests {

  private Movie starWars;
  private Movie starTrek;
  private Movie starGate;
  private Movie theShining;
  private Movie findingNemo;
  private MovieList movieList;
  private Movie legoMovie;
  private MovieList kidsList;

  @Test
  void subsets() throws Exception {
    starWars = new Movie("Star Wars", Category.SCIFI, 5);
    legoMovie = new Movie("Lego Movie", Category.Kids, 5);
    starTrek = new Movie("Star Trek", Category.SCIFI, 3);
    starGate = new Movie("Stargate", Category.SCIFI);
    theShining = new Movie("The Shining", Category.HORROR, 2);
    findingNemo = new Movie("Finding Nemo", Category.Kids, 5);

    movieList = new MovieList();
    movieList.add(starWars);
    movieList.add(legoMovie);
    movieList.add(starTrek);
    movieList.add(starGate);
    movieList.add(theShining);
    movieList.add(findingNemo);

    kidsList = new MovieList();
    kidsList.add(legoMovie);
    kidsList.add(findingNemo);

    assertThat(kidsList).isEqualTo(movieList.categorySublist(Category.Kids));
  }

  @Test
  void all_subset() throws Exception {
    starWars = new Movie("Star Wars", Category.SCIFI, 5);
    legoMovie = new Movie("Lego Movie", Category.Kids, 5);
    starTrek = new Movie("Star Trek", Category.SCIFI, 3);
    starGate = new Movie("Stargate", Category.SCIFI);
    theShining = new Movie("The Shining", Category.HORROR, 2);
    findingNemo = new Movie("Finding Nemo", Category.Kids, 5);

    movieList = new MovieList();
    movieList.add(starWars);
    movieList.add(legoMovie);
    movieList.add(starTrek);
    movieList.add(starGate);
    movieList.add(theShining);
    movieList.add(findingNemo);

    kidsList = new MovieList();
    kidsList.add(legoMovie);
    kidsList.add(findingNemo);

    assertThat(movieList).isEqualTo(movieList.categorySublist(Category.ALL));
  }
}
