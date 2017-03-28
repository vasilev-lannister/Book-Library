package com.csc.booklibrary.persistence.interfaces;

import java.util.function.Function;

import com.csc.booklibrary.common.ServiceFactory;

/**
 * The class has the role of a transaction handler class. It consists of a
 * single property of type EntityManagerFactory, a constructor to initialize the
 * property and a method named execute.
 *
 * @author mvasilev
 */

public interface TransactionHandler {

    /**
     * This method handles the EntityTransaction. Uses functional interface
     * Function to pass the unique code needed by other methods from outside.
     * May throw Exception.
     * 
     * @param transactionalCode
     *            the code to be executed using transaction
     * @return the result of the transaction
     */
    <TransactionResult> TransactionResult execute(Function<ServiceFactory<?>, TransactionResult> transactionalCode);
}
