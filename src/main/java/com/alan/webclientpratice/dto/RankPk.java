package com.alan.webclientpratice.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RankPk implements Serializable {

    private static final Long serialVersionUID=1l;

    private String summonerId;
    private String queueType;

}
