package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Vector;
import javax.swing.ListModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JListOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

/**
 * I couldn't get it to work on travis linux machine.
 */
@EnabledOnOs({OS.MAC})
class TestSwingMovieListEditorView {

  JFrameOperator mainWindow;
  private MovieList movieList;
  private Movie starWars;
  private Movie starTrek;
  private Movie starGate;
  private Vector<Object> movies;

  @BeforeEach
  void setUp() {
    SwingMovieListEditorView.start();
    movieList = new MovieList();
    starWars = new Movie("Star Wars");
    starTrek = new Movie("Star Trek");
    starGate = new Movie("Stargate");

    movies = new Vector<>();
    movies.add(starWars);
    movies.add(starTrek);
    movies.add(starGate);

    movieList = new MovieList();
    movieList.add(starWars);
    movieList.add(starTrek);
    movieList.add(starGate);
  }

  @Test
  void listContents() {
    mainWindow = new JFrameOperator("Movie List");
    MovieListEditor editor =
        new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());

    JListOperator movieList = new JListOperator(mainWindow);
    ListModel listModel = movieList.getModel();
    assertThat(listModel.getSize()).isEqualTo(movies.size());
    for (int i = 0; i < movies.size(); i++) {
      assertThat(movies.get(i)).isEqualTo(listModel.getElementAt(i));
    }
  }

  @Test
  void adding() throws Exception {
    String LOST_IN_SPACE = "Lost In Space";
    Movie lostInSpace = new Movie(LOST_IN_SPACE);
    movies.add(lostInSpace);

    mainWindow = new JFrameOperator("Movie List");
    MovieListEditor editor =
        new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());

    JTextFieldOperator newMovieField = new JTextFieldOperator(mainWindow);
    newMovieField.enterText(LOST_IN_SPACE);

    JButtonOperator addButton = new JButtonOperator(mainWindow, "Add");
    addButton.doClick();

    JListOperator movieList = new JListOperator(mainWindow);
    ListModel listModel = movieList.getModel();
    assertThat(listModel.getSize()).isEqualTo(movies.size());
    for (int i = 0; i < movies.size(); i++) {
      assertThat(movies.get(i)).isEqualTo(listModel.getElementAt(i));
    }
  }

  @Test
  void selecting() {
    mainWindow = new JFrameOperator("Movie List");
    MovieListEditor editor =
        new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());

    JListOperator movieList = new JListOperator(mainWindow);
    movieList.clickOnItem(1, 1);

    JTextFieldOperator newMovieField = new JTextFieldOperator(mainWindow);
    assertThat(newMovieField.getText()).isEqualTo("Star Trek");
  }
}
