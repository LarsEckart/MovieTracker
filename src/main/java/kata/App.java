package kata;

import com.larseckart.movietracker.Category;
import com.larseckart.movietracker.Movie;
import com.larseckart.movietracker.MovieList;
import com.larseckart.movietracker.MovieListEditor;
import com.larseckart.movietracker.SwingMovieListEditorView;
import java.io.IOException;

class App {

  public static void main(String[] args) throws IOException {
    SwingMovieListEditorView window = SwingMovieListEditorView.start();
    MovieList movieList = new MovieList();
    movieList.add(new Movie("Lars", Category.COMEDY));
    movieList.add(new Movie("Lars0", 0));
    movieList.add(new Movie("Lars1", 1));
    movieList.add(new Movie("Lars2", 2));
    movieList.add(new Movie("Lars3", 3));
    movieList.add(new Movie("Lars4", 4));
    movieList.add(new Movie("Lars5", 5));
    window.setEditor(new MovieListEditor(movieList, window));
  }
}
