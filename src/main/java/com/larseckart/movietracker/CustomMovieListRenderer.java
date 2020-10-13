package com.larseckart.movietracker;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class CustomMovieListRenderer extends JLabel implements ListCellRenderer<Movie> {

  @Override
  public Component getListCellRendererComponent(JList list, Movie value, int index,
      boolean isSelected, boolean cellHasFocus) {

    setBackground(list.getBackground());
    setForeground(list.getForeground());

    setText(value.getName());
    if (value.hasRating()) {
      setIcon(ratingIcons[value.getRating() + 1]);
    } else {
      setIcon(ratingIcons[0]);
    }
    return this;
  }

  private static ImageIcon[] ratingIcons = {
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("no-rating.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("zero.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("one.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("two.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("three.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("four.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("five.png"))
  };

  public static ImageIcon iconForRating(int rating) {
    return ratingIcons[rating+1];
  }

}