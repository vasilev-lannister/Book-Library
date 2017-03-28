package com.csc.booklibrary.initialization;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.repositories.TransactionHandlerJPA;

/**
 * This class initializes and then stops the servlet context.
 * 
 * @author mduhovnikov
 *
 */
@WebListener
public class ServletContextCreationListener implements ServletContextListener {

    private EntityManagerFactory emf = null;
    private static final Logger LOGGER = Logger.getLogger(ServletContextCreationListener.class.getName());

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        LOGGER.log(Level.INFO, "Initializing BookLibrary ServletContext...");
        emf = Persistence.createEntityManagerFactory("library-jpa-hibernate");
        
        final TransactionHandler transactionHandler = new TransactionHandlerJPA(emf);
        final ServiceFactory<TransactionHandler> serviceFactory = new ServiceFactoryCreator(transactionHandler).createServiceFactory();

        final ServletContext sc = servletContextEvent.getServletContext();
        sc.setAttribute("serviceFactory", serviceFactory);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent arg0) {

        LOGGER.log(Level.INFO, "BookLibrary ServletContext releasing...");

        if (emf != null) {
            emf.close();
        }
        LOGGER.log(Level.INFO, "BookLibrary ServletContext released.");
    }

}
