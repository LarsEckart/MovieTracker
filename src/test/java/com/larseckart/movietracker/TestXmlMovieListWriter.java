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
    MovieListWriter writer = new XMLMovieListWriter(destination);

    writer.write(movieList);

    assertThat(destination.toString()).isEqualTo("<movielist>\n</movielist>");
  }

  @Test
  void writing_list_containing_one_movie() throws Exception {
    MovieListWriter writer = new XMLMovieListWriter(destination);
    Movie starWars = new Movie("Star Wars", Category.SCIFI);
    starWars.addRating(4, "New York Times");
    movieList.add(starWars);

    writer.write(movieList);


    assertThat(destination.toString()).isEqualTo("""
    <movielist>
      <movie name="Star Wars" category="Science Fiction">
        <ratings>
          <rating value="4" source="New York Times" />
        </ratings>
      </movie>
    </movielist>""");
  }
}
