package com.larseckart.movietracker;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
import javax.swing.JTextArea;
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
  private JTextField ratingSourceField;
  private JComboBox ratingValueField;
  private JTextArea ratingReviewField;

  public SwingMovieListEditorView() {
    super();
  }

  public void init() {
    setTitle();
    setLayout();
    setJMenuBar(initMenuBar());
    getContentPane().add(initMovieListPanel());
    getContentPane().add(initDetailPanel());
    getContentPane().add(initButtonPanel());

    pack();
    setSize(600, 600);
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
    detailPanel.add(initCategoryField());
    detailPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    detailPanel.add(initRatingsPanel());
    return detailPanel;
  }

  private Component initRatingsPanel() {
    JPanel ratingsPane = new JPanel();
    ratingsPane.setLayout(new BoxLayout(ratingsPane, BoxLayout.X_AXIS));
    ratingsPane.setBorder(BorderFactory.createEtchedBorder());
    ratingsPane.add(initRatingList());
    ratingsPane.add(Box.createRigidArea(new Dimension(5, 0)));
    ratingsPane.add(initRatingsDetailPane());
    return ratingsPane;
  }

  private Component initRatingsDetailPane() {
    JPanel addRatingPane = new JPanel();
    addRatingPane.setLayout(new BoxLayout(addRatingPane, BoxLayout.Y_AXIS));

    addRatingPane.setBorder(BorderFactory.createEmptyBorder(1, 10, 10, 10));
    addRatingPane.add(initRatingValueField());
    addRatingPane.add(Box.createRigidArea(new Dimension(0, 5)));
    addRatingPane.add(initRatingSourceField());
    addRatingPane.add(Box.createRigidArea(new Dimension(0, 5)));
    addRatingPane.add(initRatingReviewField());
    addRatingPane.add(Box.createRigidArea(new Dimension(0, 5)));
    addRatingPane.add(initAddRatingButton());
    addRatingPane.add(Box.createGlue());
    return addRatingPane;
  }

  private Component initRatingReviewField() {
    ratingReviewField = new JTextArea(3, 20);
    ratingReviewField.setName("review");
    ratingReviewField.setLineWrap(true);
    ratingReviewField.setWrapStyleWord(true);

    JScrollPane scroller = new JScrollPane(ratingReviewField,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    return scroller;
  }

  private Component initAddRatingButton() {
    JButton addButton = new JButton("Add Rating");
    addButton.setName("addRating");
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        myEditor.addRating();
      }
    });
    return addButton;
  }

  private Component initRatingValueField() {
    List<ImageIcon> ratingIconsWithNA = Arrays.asList(RatingRenderer.icons());
    List<ImageIcon> ratingIconsWithoutNA = ratingIconsWithNA.subList(1, 7);
    ratingValueField = new JComboBox<>(ratingIconsWithoutNA.toArray(new ImageIcon[0]));
    ratingValueField.setName("ratingValue");
    return ratingValueField;
  }

  private Component initRatingSourceField() {
    ratingSourceField = new JTextField(16);
    ratingSourceField.setName("ratingSource");
    return ratingSourceField;
  }

  private JScrollPane initRatingList() {
    ratingList = new JList<>(new Vector<>());
    ratingList.setName("ratings");
    ratingList.setCellRenderer(new RatingRenderer());
    ratingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    ratingList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        myEditor.selectRating(ratingList.getSelectedIndex());
      }
    });
    return new JScrollPane(ratingList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
  }

  private JPanel initMovieListPanel() {
    JPanel listPane = new JPanel();
    listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
    listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    listPane.add(initCategoryFilterField());
    listPane.add(Box.createRigidArea(new Dimension(0, 5)));
    listPane.add(initMovieList());
    return listPane;
  }

  private void setTitle() {
    setTitle("Movie List");
  }

  private void setLayout() {
    getContentPane().setLayout(new FlowLayout());
  }

  private JScrollPane initMovieList() {
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
    return new JScrollPane(
        movieList,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
    categoryField = new JComboBox<>(Category.categories().toArray(new Category[0])) {
      @Override
      public Dimension getMaximumSize() {
        Dimension size = getPreferredSize();
        size.width = Short.MAX_VALUE;
        return size;
      }
    };
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

  @Override
  public int getRatingValueField() {
    return ratingValueField.getSelectedIndex();
  }

  @Override
  public String getRatingSourceField() {
    return ratingSourceField.getText();
  }

  @Override
  public void setRatingReviewField(String review) {
    ratingReviewField.setText(review);
  }

  @Override
  public String getRatingReviewField() {
    return ratingReviewField.getText();
  }

  @Override
  public void setRatingSourceField(String source) {
    ratingSourceField.setText(source);
  }

  @Override
  public void setRatingValueField(int value) {
    ratingValueField.setSelectedIndex(value);
  }



  public static SwingMovieListEditorView start() {
    SwingMovieListEditorView window = new SwingMovieListEditorView();
    window.init();
    window.setVisible(true);
    return window;
  }
}
