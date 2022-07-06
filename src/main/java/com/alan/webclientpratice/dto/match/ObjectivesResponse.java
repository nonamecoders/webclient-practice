package com.alan.webclientpratice.dto.match;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ObjectivesResponse implements Serializable {

    private ObjectiveResponse baron;
    private ObjectiveResponse champion;
    private ObjectiveResponse dragon;
    private ObjectiveResponse inhibitor;
    private ObjectiveResponse riftHerald;
    private ObjectiveResponse tower;

    @Data
    private static class ObjectiveResponse implements Serializable {
        private Boolean first;
        private Integer kills;
    }
}
