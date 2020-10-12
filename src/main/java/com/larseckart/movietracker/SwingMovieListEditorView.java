package com.larseckart.movietracker;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class SwingMovieListEditorView extends JFrame implements MovieListEditorView {

  private JList<Movie> movieList;
  private MovieListEditor myEditor;
  private JTextField movieField;

  public SwingMovieListEditorView() {
    super();
  }

  @Override
  public void setMovies(Vector<Movie> movies) {
    movieList.setListData(movies);
  }

  @Override
  public String getNewName() {
    return movieField.getText();
  }

  @Override
  public void setEditor(MovieListEditor anEditor) {
    myEditor = anEditor;
  }

  public void init() {
    setTitle("Movie List");
    getContentPane().setLayout(new FlowLayout());
    movieList = new JList<>(new Vector<>());
    JScrollPane scroller =
        new JScrollPane(
            movieList,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    getContentPane().add(scroller);

    movieField = new JTextField(16);
    getContentPane().add(movieField);

    JButton addButton = new JButton("Add");
    getContentPane().add(addButton);
    addButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        myEditor.add();
      }
    });

    pack();
  }

  public static void start() {
    SwingMovieListEditorView window = new SwingMovieListEditorView();
    window.init();
    window.setVisible(true);
  }
}
