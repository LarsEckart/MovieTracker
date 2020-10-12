package kata;

import com.larseckart.movietracker.MovieList;
import com.larseckart.movietracker.MovieListEditor;
import com.larseckart.movietracker.SwingMovieListEditorView;
import java.io.IOException;

class App {

  public static void main(String[] args) throws IOException {
    SwingMovieListEditorView window = SwingMovieListEditorView.start();
    window.setEditor(new MovieListEditor(new MovieList(), window));
  }
}
