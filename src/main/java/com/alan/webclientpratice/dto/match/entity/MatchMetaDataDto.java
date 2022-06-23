package com.alan.webclientpratice.dto.match.entity;

import com.alan.webclientpratice.mapper.StringArrayConverter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "MATCH_METADATA")
public class MatchMetaDataDto implements Serializable {

    @Id
    @Column
    private String matchId;

    @Column
    private String dataVersion;

    @Column
    @Convert(converter = StringArrayConverter.class)
    private List<String> participants;

    @Builder
    public MatchMetaDataDto(String matchId,String dataVersion, List<String> participants) {
        this.matchId = matchId;
        this.dataVersion = dataVersion;
        this.participants = participants;
    }

}