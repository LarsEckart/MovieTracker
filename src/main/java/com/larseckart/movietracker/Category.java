package com.larseckart.movietracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Category {

  private static final List<Category> allCategories = new ArrayList<>();
  private static final Map<String, Category> categoriesByName = new HashMap<>();

  public static final Category UNCATEGORIZED = new Category("Uncategorized");
  public static final Category SCIFI = new Category("Science Fiction");
  public static final Category HORROR = new Category("Horror");
  public static final Category COMEDY = new Category("Comedy");
  public static final Category DRAMA = new Category("Drama");
  public static final Category KIDS = new Category("Kids");
  public static final Category THRILLER = new Category("Thriller");
  public static final Category FANTASY = new Category("Fantasy");
  public static final Category ALL = new Category("All");

  private String name;

  private Category(String aName) {
    name = aName;
    allCategories.add(this);
    categoriesByName.put(aName, this);
  }

  public static List<Category> categories() {
    return allCategories;
  }

  public static Category getCategoryNamed(String name) {
    return categoriesByName.getOrDefault(name, UNCATEGORIZED);
  }

  @Override
  public String toString() {
    return name;
  }
}
