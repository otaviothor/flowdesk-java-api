package com.flowdesk.main.entity.relations;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IssueLabelId implements Serializable {

    @Column(name = "issue_id")
    private Long issueId;

    @Column(name = "label", length = 60)
    private String label;

    protected IssueLabelId() {}

    public IssueLabelId(Long issueId, String label) {
        this.issueId = issueId;
        this.label = label;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IssueLabelId that)) {
            return false;
        }
        return Objects.equals(issueId, that.issueId)
                && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueId, label);
    }
}
