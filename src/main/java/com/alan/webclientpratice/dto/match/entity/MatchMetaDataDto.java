package com.alan.webclientpratice.dto.match.entity;

import com.alan.webclientpratice.mapper.converter.GenericJsonConverter;
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
@Table(name = "MATCH_METADATA")
public class MatchMetaDataDto implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="MATCH_ID")
    private String matchId;

    @Column(name = "DATA_VERSION")
    private String dataVersion;

    @Column(length = 1000)
    @Convert(converter = GenericJsonConverter.class)
    private List<String> participants;

    @Builder
    public MatchMetaDataDto(String matchId,String dataVersion, List<String> participants) {
        this.matchId = matchId;
        this.dataVersion = dataVersion;
        this.participants = participants;
    }
}