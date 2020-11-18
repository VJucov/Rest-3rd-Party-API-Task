package com.example.rest3rdapitask.service.jira;

import com.example.rest3rdapitask.dto.jira.CurrentUser;
import org.springframework.http.ResponseEntity;

public interface JiraService {
    void getSession();
    ResponseEntity<CurrentUser> getCurrentUser();
}
