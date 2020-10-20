package com.larseckart.movietracker;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class CustomMovieListRenderer extends JLabel implements ListCellRenderer<Movie> {

  @Override
  public Component getListCellRendererComponent(JList list, Movie value, int index,
      boolean isSelected, boolean cellHasFocus) {

    if (isSelected) {
      setBackground(list.getSelectionBackground());
      setForeground(list.getSelectionForeground());
    } else {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }

    setText(value.getName());

    return this;
  }

}
