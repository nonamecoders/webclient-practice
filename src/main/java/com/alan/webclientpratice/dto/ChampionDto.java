package com.alan.webclientpratice.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "CHAMPION")
//@IdClass(ChampionPk.class)
public class ChampionDto implements Serializable {


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "champion_id")
    private String championId;

    @Id
    @Column(name = "champion_key")
    private Long championKey;

    private String name;

    private String title;

    @Column(name = "image_full_url")
    private String imageFullUrl;

    @Column(name = "image_sprite")
    private String imageSprite;

    private String tags;

    @Builder
    public ChampionDto(String championId, String championKey,String name,String title, String imageFullUrl, String imageSprite, String tags){

        this.championId = championId;
        this.championKey = Long.parseLong(championKey);
        this.name = name;
        this.title = title;
        this.imageFullUrl = imageFullUrl;
        this.imageSprite = imageSprite;
        this.tags = tags;
    }

}
