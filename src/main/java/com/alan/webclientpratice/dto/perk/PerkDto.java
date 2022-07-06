package com.alan.webclientpratice.dto.perk;

import com.alan.webclientpratice.mapper.converter.StringArrayConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "PERK")
public class PerkDto {
    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column(name = "MAJOR_CHANGE_PATCH_VERSION")
    private String majorChangePatchVersion;

    @Column(length = 1000)
    private String tooltip;

    @Column(name = "SHORT_DESC ",length = 1000)
    private String shortDesc;

    @Column(name = "LONG_DESC",length = 1000)
    private String longDesc;

    @Column(name = "ICON_PATH")
    private String iconPath;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "END_OF_GAME_STAT_DESCS")
    private List<String> endOfGameStatDescs;

}
