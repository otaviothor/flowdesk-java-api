package com.flowdesk.main.repository;

import com.flowdesk.main.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByKeyName(String keyName);
}
