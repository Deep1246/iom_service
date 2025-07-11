package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkflowActionDto {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("next_stage")
    private String nextStage;

    @JsonProperty("action")
    private String action;

}
