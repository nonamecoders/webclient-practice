package com.alan.webclientpratice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class RankPk implements Serializable {

    private static final Long serialVersionUID=1l;

    private String summonerId;
    private String queueType;

}
