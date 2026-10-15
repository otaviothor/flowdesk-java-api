package com.flowdesk.main.repository;

import com.flowdesk.main.entity.ProjectMember;
import com.flowdesk.main.entity.relations.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

}
