package com.example.rest3rdapitask.controller;

import com.example.rest3rdapitask.service.jira.impl.JiraServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.rest3rdapitask.util.PageUri.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CREATE_SESSION)
public class JiraController {
    private final JiraServiceImplementation jiraServiceImplementation;

    @PostMapping(AUTH)
    public ResponseEntity<String> createSession() {
        jiraServiceImplementation.getSession();
        return ResponseEntity.ok("Current session was fetched");
    }

    @GetMapping(CURRENT_USER)
    public ResponseEntity<String> getCurrentUser() {
        return ResponseEntity.ok("Hello " + jiraServiceImplementation.getCurrentUser().getBody().getName()
                + " welcome back to JIRA!");
    }
}
