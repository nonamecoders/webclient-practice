package com.alan.webclientpratice.dto.match.entity;

import com.alan.webclientpratice.dto.match.ChallengeResponse;
import com.alan.webclientpratice.dto.match.PerksResponse;
import com.alan.webclientpratice.dto.match.TeamResponse;
import com.alan.webclientpratice.mapper.converter.challenge.ChallengeConverter;
import com.alan.webclientpratice.mapper.converter.perk.PerkDataConverter;
import com.alan.webclientpratice.mapper.converter.team.TeamConverter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "MATCH_INFO")
@EqualsAndHashCode
@NamedEntityGraph(name = "MatchInfoWithMetaData",attributeNodes = @NamedAttributeNode("meta"))
public class MatchInfoDto implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long gameCreation;

    @Column
    private Long gameDuration;

    @Column
    private Long gameEndTimestamp;

    @Column
    private Long gameId;

    @Column
    private String gameMode;

    @Column
    private String gameName;

    @Column
    private Long gameStartTimestamp;

    @Column
    private String gameType;

    @Column
    private String gameVersion;

    @Column
    private Integer mapId;

    private Integer assists;

    @Column
    private Integer baronKills;

    @Column
    private Integer bountyLevel;

    @Column(length = 6000)
    @Lob
    @Convert(converter = ChallengeConverter.class)
    private ChallengeResponse challenges;

    @Column
    private Integer champExperience;

    @Column
    private Integer champLevel;

    @Column
    private Integer championId;

    @Column
    private String championName;

    @Column
    private Integer championTransform;

    @Column
    private Integer consumablesPurchased;

    @Column
    private Integer damageDealtToBuildings;

    @Column
    private Integer damageDealtToObjectives;

    @Column
    private Integer damageDealtToTurrets;

    @Column
    private Integer damageSelfMitigated;

    @Column
    private Integer deaths;

    @Column
    private Integer detectorWardsPlaced;

    @Column
    private Integer doubleKills;

    @Column
    private Integer dragonKills;

    @Column
    private Boolean eligibleForProgression;

    @Column
    private Boolean firstBloodAssist;

    @Column
    private Boolean firstBloodKill;

    @Column
    private Boolean firstTowerAssist;

    @Column
    private Boolean firstTowerKill;

    @Column
    private Boolean gameEndedInEarlySurrender;

    @Column
    private Boolean gameEndedInSurrender;

    @Column
    private Integer goldEarned;

    @Column
    private Integer goldSpent;

    @Column
    private String individualPosition;

    @Column
    private Integer inhibitorKills;

    @Column
    private Integer inhibitorTakedowns;

    @Column
    private Integer inhibitorsLost;

    @Column
    private Integer item0;

    @Column
    private Integer item1;

    @Column
    private Integer item2;

    @Column
    private Integer item3;

    @Column
    private Integer item4;

    @Column
    private Integer item5;

    @Column
    private Integer item6;

    @Column
    private Integer itemsPurchased;

    @Column
    private Integer killingSprees;

    @Column
    private Integer kills;

    @Column
    private String lane;

    @Column
    private Integer largestCriticalStrike;

    @Column
    private Integer largestKillingSpree;

    @Column
    private Integer largestMultiKill;

    @Column
    private Integer longestTimeSpentLiving;

    @Column
    private Integer magicDamageDealt;

    @Column
    private Integer magicDamageDealtToChampions;

    @Column
    private Integer magicDamageTaken;

    @Column
    private Integer neutralMinionsKilled;

    @Column
    private Integer nexusKills;

    @Column
    private Integer nexusLost;

    @Column
    private Integer nexusTakedowns;

    @Column
    private Integer objectivesStolen;

    @Column
    private Integer objectivesStolenAssists;

    @Column
    private Integer participantId;

    @Column
    private Integer pentaKills;

    @Column(length = 3000)
    @Convert(converter = PerkDataConverter.class)
    private PerksResponse perks;

    @Column
    private Integer physicalDamageDealt;

    @Column
    private Integer physicalDamageDealtToChampions;

    @Column
    private Integer physicalDamageTaken;

    @Column
    private Integer profileIcon;

    @Column
    private String puuid;

    @Column
    private Integer quadraKills;

    @Column
    private String riotIdName;

    @Column
    private String riotIdTagline;

    @Column
    private String role;

    @Column
    private Integer sightWardsBoughtInGame;

    @Column
    private Integer spell1Casts;

    @Column
    private Integer spell2Casts;

    @Column
    private Integer spell3Casts;

    @Column
    private Integer spell4Casts;

    @Column
    private Integer summoner1Casts;

    @Column
    private Integer summoner1Id;

    @Column
    private Integer summoner2Casts;

    @Column
    private Integer summoner2Id;

    @Column
    private String summonerId;

    @Column
    private Integer summonerLevel;

    @Column
    private String summonerName;

    @Column
    private Boolean teamEarlySurrendered;

    @Column
    private Integer teamId;

    @Column
    private String teamPosition;

    @Column
    private Integer timeCCingOthers;

    @Column
    private Integer timePlayed;

    @Column
    private Integer totalDamageDealt;

    @Column
    private Integer totalDamageDealtToChampions;

    @Column
    private Integer totalDamageShieldedOnTeammates;

    @Column
    private Integer totalDamageTaken;

    @Column
    private Integer totalHeal;

    @Column
    private Integer totalHealsOnTeammates;

    @Column
    private Integer totalMinionsKilled;

    @Column
    private Integer totalTimeCCDealt;

    @Column
    private Integer totalTimeSpentDead;

    @Column
    private Integer totalUnitsHealed;

    @Column
    private Integer tripleKills;

    @Column
    private Integer trueDamageDealt;

    @Column
    private Integer trueDamageDealtToChampions;

    @Column
    private Integer trueDamageTaken;

    @Column
    private Integer turretKills;

    @Column
    private Integer turretTakedowns;

    @Column
    private Integer turretsLost;

    @Column
    private Integer unrealKills;

    @Column
    private Integer visionScore;

    @Column
    private Integer visionWardsBoughtInGame;

    @Column
    private Integer wardsKilled;

    @Column
    private Integer wardsPlaced;

    @Column
    private Boolean win;

    @Column
    private String platformId;

    @Column
    private String queueId;

    @Column(length = 2000)
    @Convert(converter = TeamConverter.class)
    private List<TeamResponse> teams;

    @Column
    private String tournamentCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCH_ID")
    private MatchMetaDataDto meta;

    @Builder
    public MatchInfoDto(Long gameCreation, Long gameDuration, Long gameEndTimestamp, Long gameId, String gameMode, String gameName, Long gameStartTimestamp, String gameType, String gameVersion, Integer mapId, Integer assists, Integer baronKills, Integer bountyLevel, ChallengeResponse challenges, Integer champExperience, Integer champLevel, Integer championId, String championName, Integer championTransform, Integer consumablesPurchased, Integer damageDealtToBuildings, Integer damageDealtToObjectives, Integer damageDealtToTurrets, Integer damageSelfMitigated, Integer deaths, Integer detectorWardsPlaced, Integer doubleKills, Integer dragonKills, Boolean eligibleForProgression, Boolean firstBloodAssist, Boolean firstBloodKill, Boolean firstTowerAssist, Boolean firstTowerKill, Boolean gameEndedInEarlySurrender, Boolean gameEndedInSurrender, Integer goldEarned, Integer goldSpent, String individualPosition, Integer inhibitorKills, Integer inhibitorTakedowns, Integer inhibitorsLost, Integer item0, Integer item1, Integer item2, Integer item3, Integer item4, Integer item5, Integer item6, Integer itemsPurchased, Integer killingSprees, Integer kills, String lane, Integer largestCriticalStrike, Integer largestKillingSpree, Integer largestMultiKill, Integer longestTimeSpentLiving, Integer magicDamageDealt, Integer magicDamageDealtToChampions, Integer magicDamageTaken, Integer neutralMinionsKilled, Integer nexusKills, Integer nexusLost, Integer nexusTakedowns, Integer objectivesStolen, Integer objectivesStolenAssists, Integer participantId, Integer pentaKills, PerksResponse perks, Integer physicalDamageDealt, Integer physicalDamageDealtToChampions, Integer physicalDamageTaken, Integer profileIcon, String puuid, Integer quadraKills, String riotIdName, String riotIdTagline, String role, Integer sightWardsBoughtInGame, Integer spell1Casts, Integer spell2Casts, Integer spell3Casts, Integer spell4Casts, Integer summoner1Casts, Integer summoner1Id, Integer summoner2Casts, Integer summoner2Id, String summonerId, Integer summonerLevel, String summonerName, Boolean teamEarlySurrendered, Integer teamId, String teamPosition, Integer timeCCingOthers, Integer timePlayed, Integer totalDamageDealt, Integer totalDamageDealtToChampions, Integer totalDamageShieldedOnTeammates, Integer totalDamageTaken, Integer totalHeal, Integer totalHealsOnTeammates, Integer totalMinionsKilled, Integer totalTimeCCDealt, Integer totalTimeSpentDead, Integer totalUnitsHealed, Integer tripleKills, Integer trueDamageDealt, Integer trueDamageDealtToChampions, Integer trueDamageTaken, Integer turretKills, Integer turretTakedowns, Integer turretsLost, Integer unrealKills, Integer visionScore, Integer visionWardsBoughtInGame, Integer wardsKilled, Integer wardsPlaced, Boolean win, String platformId, String queueId, List<TeamResponse> teams, String tournamentCode, MatchMetaDataDto meta) {

        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
        this.gameEndTimestamp = gameEndTimestamp;
        this.gameId = gameId;
        this.gameMode = gameMode;
        this.gameName = gameName;
        this.gameStartTimestamp = gameStartTimestamp;
        this.gameType = gameType;
        this.gameVersion = gameVersion;
        this.mapId = mapId;
        this.assists = assists;
        this.baronKills = baronKills;
        this.bountyLevel = bountyLevel;
        this.challenges = challenges;
        this.champExperience = champExperience;
        this.champLevel = champLevel;
        this.championId = championId;
        this.championName = championName;
        this.championTransform = championTransform;
        this.consumablesPurchased = consumablesPurchased;
        this.damageDealtToBuildings = damageDealtToBuildings;
        this.damageDealtToObjectives = damageDealtToObjectives;
        this.damageDealtToTurrets = damageDealtToTurrets;
        this.damageSelfMitigated = damageSelfMitigated;
        this.deaths = deaths;
        this.detectorWardsPlaced = detectorWardsPlaced;
        this.doubleKills = doubleKills;
        this.dragonKills = dragonKills;
        this.eligibleForProgression = eligibleForProgression;
        this.firstBloodAssist = firstBloodAssist;
        this.firstBloodKill = firstBloodKill;
        this.firstTowerAssist = firstTowerAssist;
        this.firstTowerKill = firstTowerKill;
        this.gameEndedInEarlySurrender = gameEndedInEarlySurrender;
        this.gameEndedInSurrender = gameEndedInSurrender;
        this.goldEarned = goldEarned;
        this.goldSpent = goldSpent;
        this.individualPosition = individualPosition;
        this.inhibitorKills = inhibitorKills;
        this.inhibitorTakedowns = inhibitorTakedowns;
        this.inhibitorsLost = inhibitorsLost;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.itemsPurchased = itemsPurchased;
        this.killingSprees = killingSprees;
        this.kills = kills;
        this.lane = lane;
        this.largestCriticalStrike = largestCriticalStrike;
        this.largestKillingSpree = largestKillingSpree;
        this.largestMultiKill = largestMultiKill;
        this.longestTimeSpentLiving = longestTimeSpentLiving;
        this.magicDamageDealt = magicDamageDealt;
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
        this.magicDamageTaken = magicDamageTaken;
        this.neutralMinionsKilled = neutralMinionsKilled;
        this.nexusKills = nexusKills;
        this.nexusLost = nexusLost;
        this.nexusTakedowns = nexusTakedowns;
        this.objectivesStolen = objectivesStolen;
        this.objectivesStolenAssists = objectivesStolenAssists;
        this.participantId = participantId;
        this.pentaKills = pentaKills;
        this.perks = perks;
        this.physicalDamageDealt = physicalDamageDealt;
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
        this.physicalDamageTaken = physicalDamageTaken;
        this.profileIcon = profileIcon;
        this.puuid = puuid;
        this.quadraKills = quadraKills;
        this.riotIdName = riotIdName;
        this.riotIdTagline = riotIdTagline;
        this.role = role;
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
        this.spell1Casts = spell1Casts;
        this.spell2Casts = spell2Casts;
        this.spell3Casts = spell3Casts;
        this.spell4Casts = spell4Casts;
        this.summoner1Casts = summoner1Casts;
        this.summoner1Id = summoner1Id;
        this.summoner2Casts = summoner2Casts;
        this.summoner2Id = summoner2Id;
        this.summonerId = summonerId;
        this.summonerLevel = summonerLevel;
        this.summonerName = summonerName;
        this.teamEarlySurrendered = teamEarlySurrendered;
        this.teamId = teamId;
        this.teamPosition = teamPosition;
        this.timeCCingOthers = timeCCingOthers;
        this.timePlayed = timePlayed;
        this.totalDamageDealt = totalDamageDealt;
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
        this.totalDamageShieldedOnTeammates = totalDamageShieldedOnTeammates;
        this.totalDamageTaken = totalDamageTaken;
        this.totalHeal = totalHeal;
        this.totalHealsOnTeammates = totalHealsOnTeammates;
        this.totalMinionsKilled = totalMinionsKilled;
        this.totalTimeCCDealt = totalTimeCCDealt;
        this.totalTimeSpentDead = totalTimeSpentDead;
        this.totalUnitsHealed = totalUnitsHealed;
        this.tripleKills = tripleKills;
        this.trueDamageDealt = trueDamageDealt;
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
        this.trueDamageTaken = trueDamageTaken;
        this.turretKills = turretKills;
        this.turretTakedowns = turretTakedowns;
        this.turretsLost = turretsLost;
        this.unrealKills = unrealKills;
        this.visionScore = visionScore;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
        this.wardsKilled = wardsKilled;
        this.wardsPlaced = wardsPlaced;
        this.win = win;
        this.platformId = platformId;
        this.queueId = queueId;
        this.teams = teams;
        this.tournamentCode = tournamentCode;
        this.meta = meta;
    }
}
