package com.csc.booklibrary.services.dto;

import java.io.Serializable;

/**
 * This class has information about the categories.
 * 
 * @author mduhovnikov
 *
 */
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 2325676981542510027L;
    private final long categoryId;
    private final String categoryName;

    /**
     * @param categoryId
     *            Database id of the category.
     * @param categoryName
     *            Name of the category.
     */
    public CategoryDTO(long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    /**
     * @return Database id of the category.
     */
    public long getCategoryId() {
        return categoryId;
    }

    /**
     * @return Name of the category.
     */
    public String getCategoryName() {
        return categoryName;
    }
}