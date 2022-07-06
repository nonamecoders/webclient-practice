package com.alan.webclientpratice.dto.match.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubStyleBonusDto implements Serializable {
    private Integer styleId;
    private Integer perkId;
}