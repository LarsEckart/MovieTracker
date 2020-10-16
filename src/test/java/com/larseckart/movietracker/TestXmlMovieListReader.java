package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Reader;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

class TestXmlMovieListReader {

  private MovieListReader movieListReader = new XMLMovieListReader();

  private String emptyString = "";
  private String oneMoviePrefix = """
      <movielist>
          <movie name="Star Wars" category="Science Fiction">
            <ratings>
              <rating value="4" source="New York Times" />
              <rating value="3" source="Washington Post" />
              <rating value="3" source="The Independent" />
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
    MovieList movies = movieListReader.getMovies(reader);
    assertThat(movies.size()).isEqualTo(0);
  }
}
