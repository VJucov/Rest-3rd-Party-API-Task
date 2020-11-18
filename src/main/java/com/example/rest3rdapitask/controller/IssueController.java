package com.example.rest3rdapitask.controller;

import com.example.rest3rdapitask.dto.issue.Comment;
import com.example.rest3rdapitask.dto.issue.UpdateIssue;
import com.example.rest3rdapitask.dto.issue.Issue;
import com.example.rest3rdapitask.service.jira.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.rest3rdapitask.util.PageUri.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ISSUE)
public class IssueController {
    private final IssueService issuesService;

    @PostMapping(CREATE)
    public ResponseEntity<String> createIssue(@RequestBody Issue issue){
        return ResponseEntity.ok("Issue with ID " + issuesService.createIssue(issue) + " was created");
    }

    @PutMapping(EDIT)
    public ResponseEntity<String> editIssue(@RequestBody UpdateIssue updateIssue, @PathVariable String issueIdOrKey) {
        return ResponseEntity.ok(issuesService.editIssue(updateIssue, issueIdOrKey));
    }

    @PostMapping(COMMENT)
    public ResponseEntity<String> addComment(@PathVariable String issueIdOrKey, @RequestBody Comment comment) {
        return ResponseEntity.ok(issuesService.addComment(comment, issueIdOrKey));
    }

    @DeleteMapping(COMMENT_DELETE)
    public ResponseEntity<String> deleteComment(@PathVariable String issueIdOrKey, @PathVariable String id) {
        return ResponseEntity.ok(issuesService.deleteComment(issueIdOrKey, id));
    }

    @DeleteMapping(DELETE)
    public String deleteIssueById(@PathVariable String id){
        return issuesService.deleteIssueById(id);
    }

    @GetMapping(ASSIGNED_TO_USER)
    public ResponseEntity<String> getIssuesAssignedToUser(@PathVariable String username){
        return issuesService.getIssuesAssignedToUser(username);
    }
}
