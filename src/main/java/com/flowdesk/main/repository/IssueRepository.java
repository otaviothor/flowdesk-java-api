package com.flowdesk.main.repository;

import com.flowdesk.main.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByTitleOrIssueKey(String title, String issueKey);

    @Query("""
            select issue
            from Issue issue
            where issue.dueDate < :asOf
              and issue.status not in (
                  com.flowdesk.main.enums.IssueStatus.DONE,
                  com.flowdesk.main.enums.IssueStatus.CANCELED
              )
            order by issue.dueDate asc
            """)
    List<Issue> findOverdueIssues(@Param("asOf") Instant asOf);
}
