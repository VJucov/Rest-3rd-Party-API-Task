package com.example.rest3rdapitask.service.jira;

import com.example.rest3rdapitask.dto.project.Project;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<Project> getProjectByKey(String projectIdOrKey);
}
