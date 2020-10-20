package com.larseckart.movietracker;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
  private JComboBox<Category> categoryFilterField;
  private JList<Rating> ratingList;

  public SwingMovieListEditorView() {
    super();
  }

  @Override
  public void setMovies(List<Movie> movies) {
    movieList.setListData(movies.toArray(new Movie[0]));
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

  @Override
  public File getFile(String name) {
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showSaveDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile();
    } else {
      return null;
    }
  }

  @Override
  public File getFileToLoad() {
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile();
    } else {
      return null;
    }
  }

  @Override
  public void setRatings(List<Rating> ratings) {
    ratingList.setListData(ratings.toArray(new Rating[0]));
  }

  public void init() {
    setTitle();
    setLayout();
    setJMenuBar(initMenuBar());
    getContentPane().add(initListPanel());
    getContentPane().add(initDetailPanel());
    getContentPane().add(initButtonPanel());

    pack();
    setSize(300, 500);
  }

  private JMenuBar initMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);
    fileMenu.add(initOpenItem());
    fileMenu.add(initSaveAsItem());
    fileMenu.add(initSaveItem());
    return menuBar;
  }

  private JMenuItem initOpenItem() {
    JMenuItem openItem = new JMenuItem("Open");
    openItem.setName("open");
    openItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          myEditor.load();
        } catch (IOException ioException) {
          //TODO: deal with this
        }
      }
    });
    return openItem;
  }

  private JMenuItem initSaveAsItem() {
    JMenuItem saveAsItem = new JMenuItem("Save As...");
    saveAsItem.setName("saveas");
    saveAsItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          myEditor.saveAs();
        } catch (IOException ioException) {
          //TODO: deal with this
        }
      }
    });
    return saveAsItem;
  }

  private JMenuItem initSaveItem() {
    JMenuItem saveItem = new JMenuItem("Save...");
    saveItem.setName("save");
    saveItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          myEditor.save();
        } catch (IOException ioException) {
          //TODO: deal with this
        }
      }
    });
    return saveItem;
  }

  private JPanel initButtonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(1, 10, 10, 10));
    buttonPanel.add(initAddButton());
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    buttonPanel.add(initUpdateButton());
    return buttonPanel;
  }

  private JPanel initDetailPanel() {
    JPanel detailPanel = new JPanel();
    detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
    detailPanel.setBorder(BorderFactory.createEmptyBorder(1, 10, 10, 10));
    detailPanel.add(initNameField());
    detailPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    detailPanel.add(initRatingCombo());
    detailPanel.add(initRatingList());
    detailPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    detailPanel.add(initCategoryField());
    return detailPanel;
  }

  private JScrollPane initRatingList() {
    ratingList = new JList<>(new Vector<>());
    ratingList.setName("ratings");
    ratingList.setCellRenderer(new RatingRenderer());
    ratingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    return new JScrollPane(ratingList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
  }

  private JPanel initListPanel() {
    JPanel listPane = new JPanel();
    listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
    listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    listPane.add(initCategoryFilterField());
    listPane.add(Box.createRigidArea(new Dimension(0, 5)));
    listPane.add(initList());
    return listPane;
  }

  private void setTitle() {
    setTitle("Movie List");
  }

  private void setLayout() {
    getContentPane().setLayout(new FlowLayout());
  }

  private JScrollPane initList() {
    movieList = new JList<>(new Vector<>());
    movieList.setName("movieList");
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
    return scroller;
  }

  private Component initCategoryFilterField() {
    categoryFilterField = new JComboBox<>(Category.categories().toArray(new Category[0]));
    categoryFilterField.setName("categoryFilter");
    categoryFilterField.setSelectedItem(Category.ALL);
    categoryFilterField.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JComboBox<Category> source = (JComboBox<Category>) e.getSource();
        myEditor.filterOnCategory((Category) source.getSelectedItem());

      }
    });
    return categoryFilterField;
  }

  private Component initNameField() {
    movieField = new JTextField(16);
    movieField.setName("name");
    return movieField;
  }

  private Component initCategoryField() {
    categoryField = new JComboBox<>(Category.categories().toArray(new Category[0]));
    categoryField.setName("category");
    categoryField.setSelectedItem(Category.UNCATEGORIZED);
    return categoryField;
  }

  private Component initRatingCombo() {
    ratingField = new JComboBox<>(RatingRenderer.icons());
    ratingField.setName("rating");
    return ratingField;
  }

  private Component initAddButton() {
    JButton addButton = new JButton("Add");
    addButton.setName("add");
    addButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        myEditor.add();
      }
    });
    return addButton;
  }

  private Component initUpdateButton() {
    JButton updateButton = new JButton("Update");
    updateButton.setName("update");
    updateButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        myEditor.update();
      }
    });
    return updateButton;
  }

  public static SwingMovieListEditorView start() {
    SwingMovieListEditorView window = new SwingMovieListEditorView();
    window.init();
    window.setVisible(true);
    return window;
  }
}
