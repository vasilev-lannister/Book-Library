package com.csc.booklibrary.services.dto;

import java.io.Serializable;

/**
 * This class has information about the type of requests.
 * 
 * @author mvitanov
 *
 */
public class RequestTypeDTO implements Serializable {

    /**
     * Serializable id.
     */
    private static final long serialVersionUID = -2608764881105047500L;

    private final int typeId;
    private final String typeName;

    /**
     * @param typeId
     *            Database id of the request type.
     * @param typeName
     *            Name of the request type.
     */
    public RequestTypeDTO(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    /**
     * @return Database id of the request type.
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * @return Name of the request type.
     */
    public String getTypeName() {
        return typeName;
    }
}
