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
    movieList.add(new Movie("Star Wars", Category.SCIFI, 4));
    movieList.add(new Movie("Star Trek", Category.SCIFI, 3));
    movieList.add(new Movie("StarGate", Category.SCIFI, 5));
    movieList.add(new Movie("The Shining", Category.HORROR, 5));
    movieList.add(new Movie("Fellowship of the Ring", Category.FANTASY, 5));
    movieList.add(new Movie("Carrie", Category.HORROR, 1));
    movieList.add(new Movie("The Hunt for Red October", Category.THRILLER, 5));
    movieList.add(new Movie("Congo", Category.THRILLER, 5));
    movieList.add(new Movie("Princess Bride", Category.FANTASY, 3));
    window.setEditor(new MovieListEditor(movieList, window));
  }
}
