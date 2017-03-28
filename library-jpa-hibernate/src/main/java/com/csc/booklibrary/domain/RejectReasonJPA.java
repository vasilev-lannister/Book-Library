package com.csc.booklibrary.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.csc.booklibrary.persistence.interfaces.RejectReason;
import com.csc.booklibrary.persistence.interfaces.Request;

/**
 * @see RejectReason
 *
 * @author mduhovnikov
 *
 */
@Entity
@Table(name = "REJECT_REASON")
public class RejectReasonJPA implements RejectReason {
    @Id
    @Column(name = "REJECT_REASON_ID")
    private long rejectReasonId;

    @OneToMany(mappedBy = "rejectReason", targetEntity = RequestJPA.class)
    private List<Request> requests = new ArrayList<>();

    @Column(name = "TEXT")
    private String rejectReasonText;

    RejectReasonJPA() {
        // Used by JPA framework
    }

    /**
     * Constructor of the class.
     * 
     * @param rejectReasonId
     *            The id of the reject reason.
     * @param rejectReasonText
     *            The text of the reject reason.
     */
    public RejectReasonJPA(final long rejectReasonId, final String rejectReasonText) {
        assert rejectReasonId > 0;
        assert rejectReasonText != null;
        this.rejectReasonId = rejectReasonId;
        this.rejectReasonText = rejectReasonText;
    }

    @Override
    public long getRejectReasonId() {
        return rejectReasonId;
    }

    @Override
    public String getRejectReasonText() {
        return rejectReasonText;
    }

    @Override
    public void setRejectReasonText(final String rejectReasonText) {
        this.rejectReasonText = rejectReasonText;
    }

    @Override
    public List<Request> getRequests() {
        return requests;
    }

}