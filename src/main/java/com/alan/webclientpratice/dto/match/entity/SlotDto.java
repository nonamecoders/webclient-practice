package com.alan.webclientpratice.dto.match.entity;

import lombok.Data;

import java.util.List;

@Data
public class SlotDto {
    private String type;
    private String slotLabel;
    private List<Integer> perks;
}