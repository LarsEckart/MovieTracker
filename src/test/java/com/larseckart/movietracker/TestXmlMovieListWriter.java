package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestXmlMovieListWriter {

  private StringWriter destination;
  private MovieList movieList;

  @BeforeEach
  void setUp() {
    destination = new StringWriter();
    movieList = new MovieList();
  }

  @Test
  void writing_empty_list() throws Exception {
    MovieListWriter writer = new XMLMovieListWriter();

    writer.write(destination, movieList);

    assertThat(destination.toString()).isEqualTo("<movielist>\n</movielist>");
  }

  @Test
  void writing_list_containing_one_movie_with_one_rating() throws Exception {
    MovieListWriter writer = new XMLMovieListWriter();
    Movie starWars = new Movie("Star Wars", Category.SCIFI);
    starWars.addRating(new Rating(4, "New York Times"));
    movieList.add(starWars);

    writer.write(destination, movieList);

    assertThat(destination.toString()).isEqualTo("""
        <movielist>
          <movie name="Star Wars" category="Science Fiction">
            <ratings>
              <rating value="4" source="New York Times" />
            </ratings>
          </movie>
        </movielist>""");
  }

  @Test
  void writing_list_containing_one_movie_with_multiple_ratings() throws Exception {
    MovieListWriter writer = new XMLMovieListWriter();
    Movie starWars = new Movie("Star Wars", Category.SCIFI);
    starWars.addRating(new Rating(4, "New York Times"));
    starWars.addRating(new Rating(3, "Washington Post"));
    starWars.addRating(new Rating(3, "The Independent"));
    movieList.add(starWars);

    writer.write(destination, movieList);

    String actual = destination.toString();

    Approvals.verify(actual);
    assertThat(actual).isEqualTo("""
        <movielist>
          <movie name="Star Wars" category="Science Fiction">
            <ratings>
              <rating value="4" source="New York Times" />
              <rating value="3" source="Washington Post" />
              <rating value="3" source="The Independent" />
            </ratings>
          </movie>
        </movielist>""");
  }

  @Test
  void writing_list_containing_multiple_movie_with_multiple_ratings() throws Exception {
    MovieListWriter writer = new XMLMovieListWriter();
    Movie starWars = new Movie("Star Wars", Category.SCIFI);
    starWars.addRating(new Rating(4, "New York Times"));
    starWars.addRating(new Rating(3, "Washington Post"));
    starWars.addRating(new Rating(3, "The Independent"));
    movieList.add(starWars);
    Movie findingNemo = new Movie("Finding Nemo", Category.KIDS);
    findingNemo.addRating(new Rating(5, "Lars"));
    findingNemo.addRating(new Rating(5, "Oskar"));
    findingNemo.addRating(new Rating(5, "Birgit"));
    movieList.add(findingNemo);

    writer.write(destination, movieList);
    String actual = destination.toString();

    Approvals.verify(actual);
    assertThat(actual).isEqualTo("""
        <movielist>
          <movie name="Star Wars" category="Science Fiction">
            <ratings>
              <rating value="4" source="New York Times" />
              <rating value="3" source="Washington Post" />
              <rating value="3" source="The Independent" />
            </ratings>
          </movie>
          <movie name="Finding Nemo" category="Kids">
            <ratings>
              <rating value="5" source="Lars" />
              <rating value="5" source="Oskar" />
              <rating value="5" source="Birgit" />
            </ratings>
          </movie>
        </movielist>""");
  }
}
