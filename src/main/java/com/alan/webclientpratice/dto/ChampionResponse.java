package com.alan.webclientpratice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RedisHash("ChampionResponse")
public class ChampionResponse implements Serializable {

    private String championId;

    private Long championKey;

    private String name;

    private String title;

    private String imageFullUrl;

    private String imageSprite;

//    private String tagString;
    private String[] tags;

    @Builder
    public ChampionResponse(String championId, String championKey,String name,String title, String imageFullUrl, String imageSprite, String[] tags){

        this.championId = championId;
        this.championKey = Long.parseLong(championKey);
        this.name = name;
        this.title = title;
        this.imageFullUrl = imageFullUrl;
        this.imageSprite = imageSprite;
        this.tags = tags;
    }
}
