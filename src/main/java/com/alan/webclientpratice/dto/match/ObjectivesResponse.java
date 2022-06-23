package com.alan.webclientpratice.dto.match;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ObjectivesResponse {

    private ObjectiveResponse baron;
    private ObjectiveResponse champion;
    private ObjectiveResponse dragon;
    private ObjectiveResponse inhibitor;
    private ObjectiveResponse riftHerald;
    private ObjectiveResponse tower;
    @Data
    private static class ObjectiveResponse {
        private Boolean first;
        private Integer kills;
    }
}
