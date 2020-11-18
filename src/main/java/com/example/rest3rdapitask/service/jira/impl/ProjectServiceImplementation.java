package com.example.rest3rdapitask.service.jira.impl;

import com.example.rest3rdapitask.dto.project.Project;
import com.example.rest3rdapitask.service.jira.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.rest3rdapitask.util.PageUri.GET_PROJECT;
import static com.example.rest3rdapitask.util.PageUri.JIRA_BASE_URL;

@Service
@RequiredArgsConstructor
public class ProjectServiceImplementation implements ProjectService {

    private final JiraServiceImplementation jiraServiceImplementation;
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<Project> getProjectByKey(String projectIdOrKey) {
        jiraServiceImplementation.getSession();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cookie", "JSESSIONID=" + jiraServiceImplementation.sessionValue.getSessionValue());
        HttpEntity request = new HttpEntity<>(headers);
        return restTemplate.exchange(JIRA_BASE_URL + GET_PROJECT.concat(projectIdOrKey), HttpMethod.GET, request, Project.class);
    }
}
