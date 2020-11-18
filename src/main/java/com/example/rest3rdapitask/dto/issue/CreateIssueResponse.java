package com.example.rest3rdapitask.dto.issue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateIssueResponse {
    private String id;
    private String key;
    private String self;
}
