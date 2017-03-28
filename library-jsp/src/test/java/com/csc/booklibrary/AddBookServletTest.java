package com.csc.booklibrary;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.csc.booklibrary.services.dto.CategoryDTO;
import com.csc.booklibrary.services.dto.LanguageDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.dto.UserRoleDTO;
import com.csc.booklibrary.services.interfaces.BookService;
import com.csc.booklibrary.web.mocks.BookServiceMock;
import com.csc.booklibrary.web.mocks.InvocationHandlerDefault;
import com.csc.booklibrary.web.mocks.InvocationHandlerRequest;
import com.csc.booklibrary.web.mocks.TransactionHandlerMock;

/**
 * Class which tests the functionality of the AddBookServlet
 *
 * @author mduhovnikov
 *
 */
public class AddBookServletTest {
    private final AddBookServlet servlet = new AddBookServlet();
    private HttpServletRequest request;
    private HttpServletResponse response;

    private void initialize(final String name, final String isbn, final String yearPublished, final String edition,
            final String quantity, final String publisher, final String authors, final String category,
            final String language) throws ServletException, IOException {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("isbn", isbn);
        parameters.put("yearPublished", yearPublished);
        parameters.put("edition", edition);
        parameters.put("quantity", quantity);
        parameters.put("publisher", publisher);
        parameters.put("authors", authors);
        parameters.put("categoryType", category);
        parameters.put("languageType", language);
        request = (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(),
                new Class[] { HttpServletRequest.class }, new InvocationHandlerRequest(parameters));
        response = (HttpServletResponse) Proxy.newProxyInstance(HttpServletResponse.class.getClassLoader(),
                new Class[] { HttpServletResponse.class }, new InvocationHandlerDefault());
    }
    
    /**
     * Tests the successful initialization in the doGet method.
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void doGetSuccess() throws ServletException, IOException {
        // given
        initialize("", "", "", "", "", "", "", "", "");
        servlet.doGet(request, response);
        final BookService bs = new BookServiceMock(new TransactionHandlerMock());

        final List<Map<String, Object>> listCategories = new ArrayList<>();
        for (final CategoryDTO c : bs.getCategories()) {
            final Map<String, Object> map = new HashMap<>();
            map.put("name", c.getCategoryName());
            map.put("id", String.valueOf(c.getCategoryId()));
            map.put("isSelected", c.getCategoryId() == 1);
            listCategories.add(map);
        }

        final List<Map<String, Object>> listLanguages = new ArrayList<>();
        for (final LanguageDTO l : bs.getLanguages()) {
            final Map<String, Object> map = new HashMap<>();
            map.put("name", l.getLanguageName());
            map.put("id", String.valueOf(l.getLanguageId()));
            map.put("isSelected", l.getLanguageId() == 1);
            listLanguages.add(map);
        }

        // when
        final List<Map<String, Object>> expectedCategories = (List<Map<String, Object>>) ((Map<String, Object>) request
                .getAttribute("viewModel")).get("categoryList");
        final List<Map<String, Object>> expectedLanguages = (List<Map<String, Object>>) ((Map<String, Object>) request
                .getAttribute("viewModel")).get("languageList");

        // then
        assertThat(listCategories, is(expectedCategories));
        assertThat(listLanguages, is(expectedLanguages));
    }
    
    /**
     * Tests the successful creating of a book in the doPost method.
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void doPostSuccess() throws ServletException, IOException {
        // given
        initialize("Name", "1-12345-12345-X", "2000", "1", "1", "Publisher", "Authors", "1", "1");
        request.getSession().setAttribute("User", new UserDTO(1, "username", "firstName", "lastName",
                new UserRoleDTO(1, "admin"), null, null, "user@user.com", null));

        // when
        servlet.doPost(request, response);

        // then
        assertEquals(null, request.getParameter("errorMessage"));
    }
    
    /**
     * Tests the throwing of an error in case of a missing name of the book.
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void missingName() throws ServletException, IOException {
        // given
        initialize("", "1-12345-12345-X", "2000", "1", "1", "Publisher", "Authors", "1", "1");
        request.getSession().setAttribute("User", new UserDTO(1, "username", "firstName", "lastName",
                new UserRoleDTO(1, "admin"), null, null, "user@user.com", null));

        // when
        servlet.doPost(request, response);

        // then
        assertEquals("addBook.error.name",
                ((Map<String, String>) request.getAttribute("viewModel")).get("errorMessage"));
    }
    
    /**
     * Tests the throwing of an error in case of a missing ISBN of the book.
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void missingIsbn() throws ServletException, IOException {
        // given
        initialize("Name", "", "2000", "1", "1", "Publisher", "Authors", "1", "1");
        request.getSession().setAttribute("User", new UserDTO(1, "username", "firstName", "lastName",
                new UserRoleDTO(1, "admin"), null, null, "user@user.com", null));

        // when
        servlet.doPost(request, response);

        // then
        assertEquals("addBook.error.isbn",
                ((Map<String, String>) request.getAttribute("viewModel")).get("errorMessage"));
    }
    
    /**
     * Tests the throwing of an error in case of missing authors of the book.
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void missingAuthors() throws ServletException, IOException {
        // given
        initialize("Name", "1-12345-12345-X", "2000", "1", "1", "Publisher", "", "1", "1");

        // when
        servlet.doPost(request, response);

        // then
        assertEquals("addBook.error.authors",
                ((Map<String, String>) request.getAttribute("viewModel")).get("errorMessage"));
    }
    
    /**
     * Tests the throwing of an error in case of a missing year of publishment of the book.
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void missingYearPublished() throws ServletException, IOException {
        // given
        initialize("Name", "1-12345-12345-X", "", "1", "1", "Publisher", "Authors", "1", "1");

        // when
        servlet.doPost(request, response);

        // then
        assertEquals("addBook.error.yearPublished",
                ((Map<String, String>) request.getAttribute("viewModel")).get("errorMessage"));
    }
    
    /**
     * Tests the throwing of an error in case of a missing edition of the book.
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void missingEdition() throws ServletException, IOException {

        // given
        initialize("Name", "1-12345-12345-X", "2000", "", "1", "Publisher", "Authors", "1", "1");

        // when
        servlet.doPost(request, response);

        // then
        assertEquals("addBook.error.edition",
                ((Map<String, String>) request.getAttribute("viewModel")).get("errorMessage"));
    }
    
    /**
     * Tests the throwing of an error in case of missing quantity for the book.
     * 
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void missingQuantity() throws ServletException, IOException {
        // given
        initialize("Name", "1-12345-12345-X", "2000", "1", "", "Publisher", "Authors", "1", "1");

        // when
        servlet.doPost(request, response);

        // then
        assertEquals("addBook.error.quantity",
                ((Map<String, String>) request.getAttribute("viewModel")).get("errorMessage"));
    }
}
