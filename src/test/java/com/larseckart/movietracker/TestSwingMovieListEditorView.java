package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Vector;
import javax.swing.ListModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
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
    starWars = new Movie("Star Wars", 5);
    starTrek = new Movie("Star Trek", 3);
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

  @Test
  void updating() {
    mainWindow = new JFrameOperator("Movie List");
    MovieListEditor editor =
        new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());

    JListOperator movieList = new JListOperator(mainWindow);
    movieList.clickOnItem(1, 1);

    JTextFieldOperator newMovieField = new JTextFieldOperator(mainWindow);
    newMovieField.setText("Star Trek I");

    JButtonOperator updateButton = new JButtonOperator(mainWindow, "Update");
    updateButton.doClick();

    movieList.clickOnItem(0, 1);
    movieList.clickOnItem(1, 1);

    assertThat(newMovieField.getText()).isEqualTo("Star Trek I");
  }

  @Test
  void select_updates_rating() {
    mainWindow = new JFrameOperator("Movie List");
    MovieListEditor editor =
        new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());

    JListOperator movieList = new JListOperator(mainWindow);
    JComboBoxOperator ratingCombo = new JComboBoxOperator(mainWindow);
    movieList.clickOnItem(0, 1);
    assertThat(ratingCombo.getSelectedIndex()).isEqualTo(6);
  }

  @Test
  void select_updates_rating_with_same_name() {
    mainWindow = new JFrameOperator("Movie List");
    MovieListEditor editor =
        new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());

    JListOperator movieList = new JListOperator(mainWindow);
    JComboBoxOperator ratingCombo = new JComboBoxOperator(mainWindow);
    movieList.clickOnItem(0, 1);
    assertThat(ratingCombo.getSelectedIndex()).isEqualTo(6);
  }

  @Test
  void test_update_rating() {
    mainWindow = new JFrameOperator("Movie List");
    MovieListEditor editor =
        new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());

    JListOperator movieList = new JListOperator(mainWindow);
    JComboBoxOperator ratingCombo = new JComboBoxOperator(mainWindow);
    movieList.clickOnItem(0, 1);
    ratingCombo.setSelectedIndex(4);
    JButtonOperator updateButton = new JButtonOperator(mainWindow, "Update");
    updateButton.pushNoBlock();
    movieList.clickOnItem(1, 1);
    movieList.clickOnItem(0, 1);
    assertThat(ratingCombo.getSelectedIndex()).isEqualTo(4);
  }

  @Disabled("somehow doesnt find the text although it's there")
  @Test
  void duplicate_caused_by_add() {
    mainWindow = new JFrameOperator("Movie List");
    MovieListEditor editor =
        new MovieListEditor(movieList, (SwingMovieListEditorView) mainWindow.getWindow());

    JTextFieldOperator newMovieField = new JTextFieldOperator(mainWindow);
    newMovieField.enterText(starWars.getName());

    JButtonOperator addButton = new JButtonOperator(mainWindow, "Add");
    addButton.pushNoBlock();

    JDialogOperator messageDialog = new JDialogOperator("Duplicate Movie");
    JLabelOperator message = new JLabelOperator(messageDialog);

    assertThat(message.getText()).isEqualTo("That would result in a duplicate Movie.");

    JButtonOperator okButton = new JButtonOperator(messageDialog, "OK");
    okButton.doClick();
  }
}
