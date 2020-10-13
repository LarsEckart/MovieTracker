package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCustomListRenderer {

  private Movie fotr;
  private Movie starTrek;
  private CustomMovieListRenderer renderer;
  private JList list;

  @BeforeEach
  void setUp() {
    fotr = new Movie("Fellowship of The Ring", 5);
    starTrek = new Movie("Star Trek", 3);
    renderer = new CustomMovieListRenderer();
    list = new JList();
  }

  @Test
  void returnsItself() {
    Component result = renderer.getListCellRendererComponent(list, fotr, 1, false, false);

    assertSame(result, renderer);
  }

  @Test
  void contents() {
    renderer.getListCellRendererComponent(list, fotr, 1, false, false);
    assertThat(renderer.getText()).isEqualTo(fotr.getName());
    assertThat(renderer.getIcon())
        .isEqualTo(CustomMovieListRenderer.iconForRating(fotr.getRating()));

    renderer.getListCellRendererComponent(list, starTrek, 1, false, false);
    assertThat(renderer.getText()).isEqualTo(starTrek.getName());
    assertThat(renderer.getIcon())
        .isEqualTo(CustomMovieListRenderer.iconForRating(starTrek.getRating()));
  }

  @Test
  void unselected_colours() throws Exception {
    list.setBackground(Color.BLUE);
    list.setForeground(Color.RED);
    list.setSelectionBackground(Color.RED);
    list.setSelectionForeground(Color.BLUE);

    renderer.getListCellRendererComponent(list, fotr, 1, false, false);

    assertThat(renderer.getBackground()).isEqualTo(Color.BLUE);
    assertThat(renderer.getForeground()).isEqualTo(Color.RED);
  }
}
