package com.alan.webclientpratice.dto.match.entity;

import com.alan.webclientpratice.mapper.converter.IntegerArrayConverter;
import com.alan.webclientpratice.mapper.converter.style.DefaultStatModsPerSubStyleConverter;
import com.alan.webclientpratice.mapper.converter.style.SlotConverter;
import com.alan.webclientpratice.mapper.converter.style.SubStyleBonusConverter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "STYLE")
public class StyleDto implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String tooltip;

    @Column
    private String iconPath;

//    @Column
//    @Convert(converter = AssetMapConverter.class)
//    private static AssetMap assetMap;

    @Column
    private Boolean isAdvanced;

    @Column
    @Convert(converter = IntegerArrayConverter.class)
    private List<Integer> allowedSubStyles;

    @Column
    @Convert(converter = SubStyleBonusConverter.class)
    private List<SubStyleBonusDto> subStyleBonus;

    @Column(length = 1000)
    @Convert(converter = SlotConverter.class)
    private List<SlotDto> slots;

    @Column
    private String defaultPageName;

    @Column
    private Long defaultSubStyle;

    @Column
    @Convert(converter = IntegerArrayConverter.class)
    private List<Integer> defaultPerks;

    @Column
    @Convert(converter = IntegerArrayConverter.class)
    private List<Integer> defaultPerksWhenSplashed;

    @Column(length = 1000)
    @Convert(converter = DefaultStatModsPerSubStyleConverter.class)
    private List<DefaultStatModsPerSubStyleDto> defaultStatModsPerSubStyle;

}