package com.larseckart.movietracker;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class RatingRenderer extends JLabel implements ListCellRenderer<Rating> {

  private static ImageIcon[] ratingIcons = {
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("no-rating.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("zero.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("one.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("two.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("three.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("four.png")),
      new ImageIcon(CustomMovieListRenderer.class.getClassLoader().getResource("five.png"))
  };

  public static ImageIcon[] icons() {
    return ratingIcons;
  }


  public static ImageIcon iconForRating(int rating) {
    return ratingIcons[rating + 1];
  }

  @Override
  public Component getListCellRendererComponent(JList<? extends Rating> list, Rating value,
      int index, boolean isSelected, boolean cellHasFocus) {
    if (isSelected) {
      setBackground(list.getSelectionBackground());
      setForeground(list.getSelectionForeground());
    } else {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }

    setText(value.source());
    setIcon(iconForRating(value.value()));
    return this;
  }
}
