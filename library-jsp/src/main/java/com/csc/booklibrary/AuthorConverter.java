package com.csc.booklibrary;

import java.util.List;

import com.csc.booklibrary.services.dto.AuthorDTO;

/**
 * THis class is uses for converting DTO Author objects to Author objects.
 * 
 * @author mduhovnikov
 *
 */
public class AuthorConverter {

    private AuthorConverter() {
    }
    
    /**
     * This method separates the authors' names by commas.
     * 
     * @param authors The DTO list of authors.
     * @return a string containing the authors' names separated by commas
     */
    public static String delimitByCommas(final List<AuthorDTO> authors) {

        final StringBuilder authorsBuilder = new StringBuilder();
        int authorsCount = 1;
        for (final AuthorDTO author : authors) {
            authorsBuilder.append(author.getName());
            if (authorsCount < authors.size()) {
                authorsBuilder.append(", ");
            }
            authorsCount++;
        }

        return authorsBuilder.toString();
    }
}
