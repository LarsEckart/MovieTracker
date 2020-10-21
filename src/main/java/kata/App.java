package kata;

import com.larseckart.movietracker.MovieList;
import com.larseckart.movietracker.MovieListEditor;
import com.larseckart.movietracker.SwingMovieListEditorView;
import java.io.File;
import java.io.IOException;

class App {

  public static void main(String[] args) {
    SwingMovieListEditorView window = SwingMovieListEditorView.start();
    MovieList movieList = new MovieList();
    MovieListEditor anEditor = new MovieListEditor(movieList, window);
    window.setEditor(anEditor);

    try {
      File file = new File(System.getProperty("user.home") + File.separator + "movies.xml");
      anEditor.load(file);
    } catch (IOException e) {
      //start with empty editor
    }
  }
}
