package com.alan.webclientpratice.mapper;

import com.alan.webclientpratice.dto.ChampionDto;
import com.alan.webclientpratice.dto.ChampionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "champion")
public interface ChampionMapper {
    ChampionMapper INSTANCE = Mappers.getMapper(ChampionMapper.class);

    ChampionResponse toChampionResponse(ChampionDto championDto);

    default String[] toTagsArray(String tags){

        return tags.split(",");
    }

}
