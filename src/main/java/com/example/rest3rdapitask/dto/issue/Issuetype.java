package com.example.rest3rdapitask.dto.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Issuetype {
    @JsonProperty("name")
    private String name;

    @JsonProperty("set")
    public Set set;
}
