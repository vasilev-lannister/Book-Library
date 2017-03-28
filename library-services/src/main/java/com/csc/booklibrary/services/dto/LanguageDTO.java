package com.csc.booklibrary.services.dto;

import java.io.Serializable;

/**
 * This class has information about the languages.
 * 
 * @author mduhovnikov
 *
 */
public class LanguageDTO implements Serializable {

    private static final long serialVersionUID = 2325676981542510027L;
    private final long languageId;
    private final String languageName;

    /**
     * @param languageId
     *            Database id of the language.
     * @param languageName
     *            Name of the language.
     */
    public LanguageDTO(long languageId, String languageName) {
        this.languageId = languageId;
        this.languageName = languageName;
    }

    /**
     * @return Database id of the language.
     */
    public long getLanguageId() {
        return languageId;
    }

    /**
     * @return Name of the language.
     */
    public String getLanguageName() {
        return languageName;
    }
}