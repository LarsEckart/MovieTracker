package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

class TestXmlMovieListReader {

  private MovieListReader movieListReader = new XMLMovieListReader();

  private String emptyString = "";
  private String oneMoviePrefix = """
      <movielist>
          <movie name="Star Wars" category="Science Fiction">
            <ratings>
              <rating value="5" source="New York Times" />
              <rating value="3" source="Washington Post" />
            </ratings>
          </movie>""";
  private String oneMovie = oneMoviePrefix + "</movielist>";

  private String multiMovieString = oneMoviePrefix + """
        <movie name="Finding Nemo" category="Kids">
          <ratings>
            <rating value="5" source="Lars" />
            <rating value="5" source="Oskar" />
            <rating value="5" source="Birgit" />
          </ratings>
        </movie>
      </movielist>""";

  @Test
  void empty_string_to_empty_movie_list() throws Exception {
    var reader = new StringReader(emptyString);
    MovieList movies = movieListReader.read(reader);
    assertThat(movies.size()).isEqualTo(0);
  }

  @Test
  void reading_one_movie() throws Exception {
    var reader = new StringReader(oneMovie);
    MovieList movies = movieListReader.read(reader);
    assertAll(
        () -> assertThat(movies.size()).isEqualTo(1),
        () -> assertThat(movies.getMovie(0).getName()).isEqualTo("Star Wars"),
        () -> assertThat(movies.getMovie(0).getCategory()).isEqualTo(Category.SCIFI),
        () -> assertThat(movies.getMovie(0).getRating()).isEqualTo(4));
  }

  @Test
  void reading_multiple_movies() throws Exception {
    var reader = new StringReader(multiMovieString);
    MovieList movies = movieListReader.read(reader);
    assertAll(
        () -> assertThat(movies.size()).isEqualTo(2),
        () -> assertThat(movies.getMovie(0).getName()).isEqualTo("Star Wars"),
        () -> assertThat(movies.getMovie(0).getCategory()).isEqualTo(Category.SCIFI),
        () -> assertThat(movies.getMovie(0).getRating()).isEqualTo(4),
        () -> assertThat(movies.getMovie(1).getName()).isEqualTo("Finding Nemo"),
        () -> assertThat(movies.getMovie(1).getCategory()).isEqualTo(Category.KIDS),
        () -> assertThat(movies.getMovie(1).getRating()).isEqualTo(5));
  }
}
