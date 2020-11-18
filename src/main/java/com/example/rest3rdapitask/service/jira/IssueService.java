package com.example.rest3rdapitask.service.jira;

import com.example.rest3rdapitask.dto.issue.Comment;
import com.example.rest3rdapitask.dto.issue.UpdateIssue;
import com.example.rest3rdapitask.dto.issue.Issue;
import org.springframework.http.ResponseEntity;

public interface IssueService {
    String createIssue(Issue issue);

    String deleteIssueById(String id);

    String editIssue(UpdateIssue updateIssue, String issueIdOrKey);

    String addComment(Comment comment, String issueIdOrKey);

    String deleteComment(String issueIdOrKey, String id);

    ResponseEntity<String> getIssuesAssignedToUser(String name);
}
