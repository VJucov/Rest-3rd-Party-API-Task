package com.example.rest3rdapitask.controller;

import com.example.rest3rdapitask.dto.project.Project;
import com.example.rest3rdapitask.service.jira.impl.ProjectServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.rest3rdapitask.util.PageUri.PROJECT;
import static com.example.rest3rdapitask.util.PageUri.GET_PROJECT_BY_IDKEY;

@RestController
@RequiredArgsConstructor
@RequestMapping(PROJECT)
public class ProjectController {
    private final ProjectServiceImplementation projectServiceImplementation;

    @GetMapping(GET_PROJECT_BY_IDKEY)
    public ResponseEntity<Project> getProjectByKey(@PathVariable String projectIdOrKey) {
        return projectServiceImplementation.getProjectByKey(projectIdOrKey);
    }
}
