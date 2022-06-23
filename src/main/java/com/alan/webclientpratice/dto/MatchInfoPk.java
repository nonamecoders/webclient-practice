package com.alan.webclientpratice.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MatchInfoPk implements Serializable {

    private static final Long serialVersionUID=1l;

    private Long gameId;
    private String puuid;

}
