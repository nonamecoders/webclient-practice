package com.alan.webclientpratice.mapper;

import com.alan.webclientpratice.dto.match.*;
import com.alan.webclientpratice.dto.match.entity.MatchInfoDto;
import com.alan.webclientpratice.dto.match.entity.MatchMetaDataDto;
import com.google.gson.Gson;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "matchInfo")
public interface MatchInfoDataMapper {

    MatchInfoDataMapper INSTANCE = Mappers.getMapper(MatchInfoDataMapper.class);

    MatchInfoDto toMatchInfoDto(ParticipantResponse participantResponse, InfoResponse infoResponse, MatchMetaDataDto meta);

}