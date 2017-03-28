package com.csc.booklibrary.persistence.interfaces;

public enum Language {
  ENGLISH(1, "English"), BULGARIAN(2, "Bulgarian"), GERMAN(3, "German"), 
  RUSSIAN(4, "Russian"), SPANISH(5, "Spanish"), FRENCH(6, "French");

  private final long languageId;
  private final String languageName;
  
  private Language(final long languageId, final String languageName) {
    this.languageId = languageId;
    this.languageName = languageName;
  }

  public long getLanguageId() {
    return languageId;
  }

  public String getLanguageName() {
    return languageName;
  }

  public String getLanguageFromId(long languageId) {
    for (Language language : Language.values()) {
      if (language.getLanguageId() == languageId) {
        return language.getLanguageName();
      }
    }
    return null;
  }
}
