package com.flowdesk.main.repository;

import com.flowdesk.main.entity.IssueLabel;
import com.flowdesk.main.entity.relations.IssueLabelId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueLabelRepository extends JpaRepository<IssueLabel, IssueLabelId> {
}
