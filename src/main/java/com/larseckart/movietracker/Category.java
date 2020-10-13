package com.larseckart.movietracker;

class Category {

  private String name;

  private Category(String name) {
    this.name = name;
  }

  public static final Category UNCATEGORIZED = new Category("Uncategorized");
  public static final Category SCIFI = new Category("Science Fiction");
  public static final Category HORROR = new Category("Horror");
  public static final Category COMEDY = new Category("Comedy");
  public static final Category DRAMA = new Category("Drama");
  public static final Category Kids = new Category("Kids");
  public static final Category THRILLER = new Category("Thriller");
}
