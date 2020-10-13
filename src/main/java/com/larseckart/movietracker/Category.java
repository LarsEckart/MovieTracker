package com.larseckart.movietracker;

class Category {

  private String name;

  private Category(String name) {
    this.name = name;
  }

  public static final Category UNCATEGORIZED = new Category("Uncategorized");
  public static final Category SCIFI = new Category("Science Fiction");
  public static final Category HORROR = new Category("Horror");
}
