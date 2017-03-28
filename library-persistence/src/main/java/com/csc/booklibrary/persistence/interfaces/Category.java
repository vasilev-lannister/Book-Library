package com.csc.booklibrary.persistence.interfaces;

public enum Category {
  FICTION(1, "Fiction"), DRAMA(2, "Drama"), FANTASY(3, "Fantasy"), COMEDY(4, "Comedy");

  private final long categoryId;
  private final String categoryName;
  
  private Category(final long categoryId, final String categoryName) {
    this.categoryId = categoryId;
    this.categoryName = categoryName;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public String getCategoryFromId(long categoryId) {
    for (Category category : Category.values()) {
      if (category.getCategoryId() == categoryId) {
        return category.getCategoryName();
      }
    }
    return null;
  }
}