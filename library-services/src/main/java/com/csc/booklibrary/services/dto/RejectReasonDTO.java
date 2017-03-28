package com.csc.booklibrary.services.dto;

import java.io.Serializable;

/**
 * This class has information about reasons for rejecting requests.
 * 
 * @author mvitanov
 *
 */
public class RejectReasonDTO implements Serializable {

    /**
     * Serializable id.
     */
    private static final long serialVersionUID = 8786704304628490470L;

    private final long rejectReasonId;
    private final String rejectReasonName;

    /**
     * @param rejectReasonId
     *            Database id of the reject reason.
     * @param rejectReasonName
     *            Name of the reject reason.
     */
    public RejectReasonDTO(long rejectReasonId, String rejectReasonName) {
        this.rejectReasonId = rejectReasonId;
        this.rejectReasonName = rejectReasonName;
    }

    /**
     * @return Database id of the reject reason.
     */
    public long getRejectReasonId() {
        return rejectReasonId;
    }

    /**
     * @return Name of the reject reason.
     */
    public String getRejectReasonName() {
        return rejectReasonName;
    }
}
