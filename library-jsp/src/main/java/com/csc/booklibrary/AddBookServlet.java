package com.csc.booklibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.services.dto.CategoryDTO;
import com.csc.booklibrary.services.dto.CreateBookDTO;
import com.csc.booklibrary.services.dto.LanguageDTO;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.exceptions.InvalidFieldDataInputException;
import com.csc.booklibrary.services.interfaces.BookService;
import com.csc.booklibrary.web.exceptions.InvalidIdParameterException;
import com.csc.booklibrary.web.utils.LocalFunctions;

/**
 * A servlet that adds a new book.
 * 
 * @author mduhovnikov
 *
 */
public class AddBookServlet extends HttpServlet {

    private static final long serialVersionUID = -4967798208970174768L;
    private static final Logger LOGGER = Logger.getLogger(AddBookServlet.class.getName());
    private static final String PAGE = "/WEB-INF/jsp/pages/addBookContent.jsp";
    private static final String SERVICE_FACTORY = "serviceFactory";
    private static final String IS_SELECTED = "isSelected";
    private static final String YEAR_PUBLISHED = "yearPublished";
    private static final String PUBLISHER = "publisher";
    private static final String AUTHORS = "authors";
    private static final String EDITION = "edition";
    private static final String QUANTITY = "quantity";
    private static final String CATEGORY_TYPE = "categoryType";
    private static final String LANGUAGE_TYPE = "languageType";

    /**
     * A form that redirects to books' page.
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute(SERVICE_FACTORY);
        final BookService addBookService = serviceFactory.findService(BookService.class);

        final Map<String, List<Map<String, Object>>> viewModel = new HashMap<>();
        final List<Map<String, Object>> categoryList = createCategoryList(addBookService, 1);
        final List<Map<String, Object>> languageList = createLanguageList(addBookService, 1);
        viewModel.put("categoryList", categoryList);
        viewModel.put("languageList", languageList);

        request.setAttribute("viewModel", viewModel);
        request.getRequestDispatcher(PAGE).forward(request, response);
    }

    /**
     * This method creates a new book. All of the details for it are taken from
     * the post form. In case of incorrect data it saves the entered values and
     * reloads the page.
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute(SERVICE_FACTORY);
        final BookService addBookService = serviceFactory.findService(BookService.class);
        assert addBookService != null;

        final String name = request.getParameter("name");
        final String isbn = request.getParameter("isbn");
        final String publisher = request.getParameter(PUBLISHER);
        final String authorsList = request.getParameter(AUTHORS);
        if (!LocalFunctions.checkIfNullOrEmpty(authorsList)) {
            processError(AUTHORS, "addBook.error.authors", request, response);
            request.getRequestDispatcher(PAGE).forward(request, response);
            return;
        }
        final List<String> authors = Arrays.asList(authorsList.split(", "));
        final Integer category = checkForIntExceptions(CATEGORY_TYPE, request, response);
        final Integer language = checkForIntExceptions(LANGUAGE_TYPE, request, response);
        final Integer yearPublished = checkForIntExceptions(YEAR_PUBLISHED, request, response);
        final Integer edition = checkForIntExceptions(EDITION, request, response);
        final Integer quantity = checkForIntExceptions(QUANTITY, request, response);
        Integer[] integerArray = { category, language, yearPublished, edition, quantity };
        for (Integer integer : integerArray) {
            if (integer == null) {
                request.getRequestDispatcher(PAGE).forward(request, response);
                return;
            }
        }

        try {
            final CreateBookDTO createdBook = new CreateBookDTO(name, isbn, yearPublished, edition, category, language,
                    quantity);
            final UserDTO user = (UserDTO) request.getSession().getAttribute("User");

            addBookService.addBook(createdBook, publisher, authors, category, language, user.getId());
            response.sendRedirect("books");

        } catch (final InvalidFieldDataInputException e) {

            LOGGER.log(Level.INFO, "Invalid input in field.", e);
            final String fieldName = e.getFieldName();
            final String message = invalidInputIn(fieldName);
            processError(fieldName, message, request, response);
            request.getRequestDispatcher(PAGE).forward(request, response);
            return;
        }
    }

    private void processError(final String errorField, final String errorMessage, final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {

        final ServletContext sc = request.getServletContext();
        final ServiceFactory<?> serviceFactory = (ServiceFactory<?>) sc.getAttribute(SERVICE_FACTORY);
        final BookService addBookService = serviceFactory.findService(BookService.class);

        final Integer category = checkForIntExceptions(CATEGORY_TYPE, request, response);
        final Integer language = checkForIntExceptions(LANGUAGE_TYPE, request, response);

        final List<Map<String, Object>> categoryList = createCategoryList(addBookService, category);
        final List<Map<String, Object>> languageList = createLanguageList(addBookService, language);

        final Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("name", request.getParameter("name"));
        viewModel.put("isbn", request.getParameter("isbn"));
        viewModel.put(PUBLISHER, request.getParameter(PUBLISHER));
        viewModel.put(AUTHORS, request.getParameter(AUTHORS));
        viewModel.put(YEAR_PUBLISHED, request.getParameter(YEAR_PUBLISHED));
        viewModel.put(EDITION, request.getParameter(EDITION));
        viewModel.put(QUANTITY, request.getParameter(QUANTITY));
        viewModel.put("categoryList", categoryList);
        viewModel.put("languageList", languageList);
        viewModel.put("category", request.getParameter(CATEGORY_TYPE));
        viewModel.put("language", request.getParameter(LANGUAGE_TYPE));
        viewModel.put("errorMessage", errorMessage);
        viewModel.put("errorField", errorField);

        request.setAttribute("viewModel", viewModel);
    }

    private Integer checkForIntExceptions(final String requestParameter, final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        Integer parameter = null;
        try {
            parameter = LocalFunctions.stringToInt(request.getParameter(requestParameter));

        } catch (final InvalidIdParameterException e) {

            LOGGER.log(Level.INFO, "Incorrect number format", e);
            final String message = invalidInputIn(requestParameter);
            processError(requestParameter, message, request, response);
        }
        return parameter;
    }

    private String invalidInputIn(final String field) {

        String message = "";
        final String[] fieldsArray = { "name", "isbn", AUTHORS, YEAR_PUBLISHED, "category", "language", EDITION,
                QUANTITY };
        final List<String> fields = Arrays.asList(fieldsArray);

        for (final String f : fields) {
            if (f.equals(field)) {
                message += "addBook.error." + f;
                break;
            }
        }
        return message;
    }

    private List<Map<String, Object>> createLanguageList(final BookService addBookService, final long selected) {
        final List<Map<String, Object>> languageList = new ArrayList<>();
        for (final LanguageDTO ld : addBookService.getLanguages()) {
            final Map<String, Object> languageType = new HashMap<>();
            languageType.put("name", ld.getLanguageName());
            languageType.put("id", String.valueOf(ld.getLanguageId()));
            if (ld.getLanguageId() == selected) {
                languageType.put(IS_SELECTED, Boolean.TRUE);
            } else {
                languageType.put(IS_SELECTED, Boolean.FALSE);
            }
            languageList.add(languageType);
        }
        return languageList;
    }

    private List<Map<String, Object>> createCategoryList(final BookService addBookService, final long selected) {
        final List<Map<String, Object>> categoryList = new ArrayList<>();
        for (final CategoryDTO cd : addBookService.getCategories()) {
            final Map<String, Object> categoryType = new HashMap<>();
            categoryType.put("name", cd.getCategoryName());
            categoryType.put("id", String.valueOf(cd.getCategoryId()));
            if (cd.getCategoryId() == selected) {
                categoryType.put(IS_SELECTED, Boolean.TRUE);
            } else {
                categoryType.put(IS_SELECTED, Boolean.FALSE);
            }
            categoryList.add(categoryType);
        }
        return categoryList;
    }
}