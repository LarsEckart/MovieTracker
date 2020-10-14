package com.larseckart.movietracker;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SwingMovieListEditorView extends JFrame implements MovieListEditorView {

  private MovieListEditor myEditor;
  private JList<Movie> movieList;
  private JTextField movieField;
  private JComboBox<ImageIcon> ratingField;
  private JComboBox<Category> categoryField;

  public SwingMovieListEditorView() {
    super();
  }

  @Override
  public void setMovies(Vector<Movie> movies) {
    movieList.setListData(movies);
  }

  @Override
  public String getNameField() {
    return movieField.getText();
  }

  @Override
  public void setNameField(String name) {
    movieField.setText(name);
  }

  @Override
  public int getRatingField() {
    return ratingField.getSelectedIndex();
  }

  @Override
  public void setRatingField(int rating) {
    ratingField.setSelectedIndex(rating);
  }

  @Override
  public void setEditor(MovieListEditor anEditor) {
    myEditor = anEditor;
  }

  @Override
  public void duplicateException(String name) {
    JOptionPane
        .showMessageDialog(this,
            "That would result in a duplicate Movie.",
            "Duplicate Movie",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setCategoryField(Category category) {
    categoryField.setSelectedItem(category);
  }

  @Override
  public Category getCategoryField() {
    return (Category) categoryField.getSelectedItem();
  }

  public void init() {
    setTitle();
    setLayout();
    initList();
    initNameField();
    initCategoryField();
    initRatingBox();
    initAddButton();
    initUpdateButton();
    pack();
    setSize(300, 400);
  }

  private void setTitle() {
    setTitle("Movie List");
  }

  private void setLayout() {
    getContentPane().setLayout(new FlowLayout());
  }

  private void initList() {
    movieList = new JList<>(new Vector<>());
    movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    movieList.setCellRenderer(new CustomMovieListRenderer());
    movieList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        myEditor.select(movieList.getSelectedIndex());
      }
    });
    JScrollPane scroller =
        new JScrollPane(
            movieList,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    getContentPane().add(scroller);
  }

  private void initNameField() {
    movieField = new JTextField(16);
    getContentPane().add(movieField);
  }

  private void initCategoryField() {
    categoryField = new JComboBox<>(Category.categories());
    categoryField.setSelectedItem(Category.UNCATEGORIZED);
    getContentPane().add(categoryField);
  }

  private void initRatingBox() {
    ratingField = new JComboBox<>(CustomMovieListRenderer.icons());
    getContentPane().add(ratingField);
  }

  private void initAddButton() {
    JButton addButton = new JButton("Add");
    getContentPane().add(addButton);
    addButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        myEditor.add();
      }
    });
  }

  private void initUpdateButton() {
    JButton updateButton = new JButton("Update");
    getContentPane().add(updateButton);
    updateButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        myEditor.update();
      }
    });
  }

  public static SwingMovieListEditorView start() {
    SwingMovieListEditorView window = new SwingMovieListEditorView();
    window.init();
    window.setVisible(true);
    return window;
  }
}
