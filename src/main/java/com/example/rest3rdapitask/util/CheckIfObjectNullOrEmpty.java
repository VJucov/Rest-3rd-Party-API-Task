package com.example.rest3rdapitask.util;

import com.example.rest3rdapitask.dto.issue.Issue;

public class CheckIfObjectNullOrEmpty {
    public static boolean checkIfIssueFieldsIsNullOrEmpty(Issue issue) {
        return issue.getFields().getDescription() != null && issue.getFields().getDescription().length() != 0 &&
                issue.getFields().getIssuetype() != null && issue.getFields().getIssuetype().getName().length() != 0 &&
                issue.getFields().getLabels() != null && !issue.getFields().getLabels().isEmpty() &&
                issue.getFields().getProject() != null && issue.getFields().getProject().getKey().length() != 0 &&
                issue.getFields().getSummary() != null && issue.getFields().getSummary().length() != 0;
    }
}
