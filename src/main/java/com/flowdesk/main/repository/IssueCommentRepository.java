package com.flowdesk.main.repository;

import com.flowdesk.main.entity.IssueComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueCommentRepository extends JpaRepository<IssueComment, Long> {
}
