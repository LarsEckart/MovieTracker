package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

class XMLMovieListReader implements MovieListReader {

  @Override
  public MovieList read(Reader reader) throws IOException {
    SAXBuilder builder = new SAXBuilder();
    Document theMovieListDocument;
    try {
      theMovieListDocument = builder.build(reader);
    } catch (JDOMException e) {
      if (e.getMessage().contains("Premature end")) {
        return new MovieList();
      }
      throw new IOException(e);
    }
    MovieList theNewList = processDocument(theMovieListDocument);
    return theNewList;
  }

  private MovieList processDocument(Document theMovieListDocument) throws IOException {
    MovieList movieList = new MovieList();

    Element movieListElement = theMovieListDocument.getRootElement();
    List<Element> movies = movieListElement.getChildren();
    for (Element movieElement : movies) {
      movieList.add(processMovie(movieElement));
    }

    return movieList;
  }

  private Movie processMovie(Element movieElement) throws IOException {
    String name = movieElement.getAttributeValue("name");
    Category category = Category.getCategoryNamed(movieElement.getAttributeValue("category"));
    Movie movie = new Movie(name, category);

    Element ratingsElement = movieElement.getChild("ratings");
    List<Element> ratingElements = ratingsElement.getChildren();
    for (Element ratingElement : ratingElements) {
      movie.addRating(processRating(ratingElement));
    }
    return movie;
  }

  private Rating processRating(Element ratingElement) throws IOException {
    Rating rating;
    try {
      int value = ratingElement.getAttribute("value").getIntValue();
      String source = ratingElement.getAttributeValue("source");
      rating = new Rating(value, source);
    } catch (DataConversionException e) {
      throw new IOException(e);
    }
    return rating;
  }
}
