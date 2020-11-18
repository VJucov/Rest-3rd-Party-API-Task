package com.example.rest3rdapitask.service.jira.impl;

import com.example.rest3rdapitask.dto.issue.Comment;
import com.example.rest3rdapitask.dto.issue.CreateIssueResponse;
import com.example.rest3rdapitask.dto.issue.UpdateIssue;
import com.example.rest3rdapitask.dto.issue.Issue;
import com.example.rest3rdapitask.exception.EmptyFieldException;
import com.example.rest3rdapitask.service.jira.IssueService;
import com.example.rest3rdapitask.util.CheckIfObjectNullOrEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.example.rest3rdapitask.util.PageUri.*;

@Service
@RequiredArgsConstructor
public class IssueServiceImplementation implements IssueService {
    private final JiraServiceImplementation jiraServiceImplementation;
    private final RestTemplate restTemplate;

    @Override
    public String createIssue(Issue issue) {
        if (CheckIfObjectNullOrEmpty.checkIfIssueFieldsIsNullOrEmpty(issue)) {
            HttpEntity request = new HttpEntity(issue, getHeader());
            return restTemplate.exchange(JIRA_BASE_URL + CREATE_ISSUE, HttpMethod.POST, request,
                    CreateIssueResponse.class).getBody().getId();
        } else {
            try {
                throw new EmptyFieldException("Please fill all necessary fields");
            } catch (HttpClientErrorException httpClientErrorException){
                return "You are not authorized, please sign in with your JIRA account";
            }
            catch (EmptyFieldException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
    }

    @Override
    public String editIssue(UpdateIssue updateIssue, String issueIdOrKey) {
        HttpEntity request = new HttpEntity(updateIssue, getHeader());
        try {
            restTemplate.exchange(JIRA_BASE_URL + EDIT_ISSUE.concat(issueIdOrKey), HttpMethod.PUT,
                    request,
                    String.class);
            return "Issue with id(key) - " + issueIdOrKey + " was edited successfully";
        } catch (HttpClientErrorException httpClientErrorException) {
            return "You are not authorized, please sign in with your JIRA account";
        } catch (Exception  e) {
            return "No issue with ID " + issueIdOrKey;
        }
    }

    @Override
    public String addComment(Comment comment, String issueIdOrKey) {
        HttpEntity request = new HttpEntity(comment, getHeader());
        try {
            restTemplate.exchange(JIRA_BASE_URL + ADD_COMMENT1.concat(issueIdOrKey).concat(ADD_COMMENT2),
                    HttpMethod.POST, request,
                    String.class);
            return "Added a comment to issue with id(key) - " + issueIdOrKey;
        } catch (HttpClientErrorException clientErrorException){
            return "You don't have permission to delete this issue";
        }
        catch (Exception  e) {
            return "No Issue with ID " + issueIdOrKey;
        }
    }

    @Override
    public String deleteComment(String issueIdOrKey, String id) {
        HttpEntity request = new HttpEntity(getHeader());
        try {
            restTemplate.exchange(JIRA_BASE_URL + DELETE_COMMENT1.concat(issueIdOrKey).concat(DELETE_COMMENT2).concat(id),
                    HttpMethod.DELETE, request,
                    String.class);
            return "Deleted comment to issue with id(key) - " + issueIdOrKey;
        } catch (HttpClientErrorException clientErrorException){
            return "You don't have permission to delete this issue";
        }
        catch (Exception  e) {
            return "No Issue with ID " + issueIdOrKey;
        }
    }

    @Override
    public String deleteIssueById(String id) {
        HttpEntity request = new HttpEntity(getHeader());
        try {
            restTemplate.exchange(JIRA_BASE_URL + DELETE_ISSUE.concat(id),
                    HttpMethod.DELETE, request,
                    String.class);
            return "Issue with id - " + id + " was deleted successfully";
        } catch (HttpClientErrorException clientErrorException){
            return "You don't have permission to delete this issue";
        }
        catch (Exception  e) {
            return "No Issue with ID " + id;
        }
    }

    @Override
    public ResponseEntity<String> getIssuesAssignedToUser(String name) {
        String url = JIRA_BASE_URL.concat(GET_ALL_ISSUES_ASSIGNED_TO_USER).concat(name);
        HttpEntity request = new HttpEntity<>(getHeader());
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        return result;
    }

    private HttpHeaders getHeader() {
        jiraServiceImplementation.getSession();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cookie", "JSESSIONID=" + jiraServiceImplementation.sessionValue.getSessionValue());
        return headers;
    }
}
