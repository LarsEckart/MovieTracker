package com.larseckart.movietracker;

import java.util.Vector;

public class Category {

  private static final Vector<Category> allCategories = new Vector<>();

  public static final Category UNCATEGORIZED = new Category("Uncategorized");
  public static final Category SCIFI = new Category("Science Fiction");
  public static final Category HORROR = new Category("Horror");
  public static final Category COMEDY = new Category("Comedy");
  public static final Category DRAMA = new Category("Drama");
  public static final Category Kids = new Category("Kids");
  public static final Category THRILLER = new Category("Thriller");
  public static final Category FANTASY = new Category("Fantasy");
  public static final Category ALL = new Category("ALL");

  private String name;

  private Category(String name) {
    this.name = name;
    allCategories.add(this);
  }

  public static Vector<Category> categories() {
    return allCategories;
  }

  @Override
  public String toString() {
    return name;
  }
}
