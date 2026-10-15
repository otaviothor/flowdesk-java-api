package com.flowdesk.main.entity;

import com.flowdesk.main.entity.relations.IssueLabelId;
import jakarta.persistence.*;

@Entity
@Table(name = "issue_labels")
public class IssueLabel {

    @EmbeddedId
    private IssueLabelId id;

    @MapsId("issueId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    protected IssueLabel() {}
}
