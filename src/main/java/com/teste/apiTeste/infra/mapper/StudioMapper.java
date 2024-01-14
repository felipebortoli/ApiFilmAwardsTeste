package com.teste.apiTeste.infra.mapper;

import com.teste.apiTeste.domain.entity.Producer;
import com.teste.apiTeste.domain.entity.Studio;
import com.teste.apiTeste.infra.persistence.StudioEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class StudioMapper {

    public List<StudioEntity> toEntity(List<Studio> studioList){
        List<StudioEntity> returnList = new ArrayList<>();
        for(Studio studio : studioList){
            StudioEntity studioEntity = new StudioEntity();
            studioEntity.setId(studio.id());
            studioEntity.setName(studio.name());
            returnList.add(studioEntity);
        }
        return returnList;
    }

    public List<Studio> toDomainObj(String[] producers){
        List<String> studioList = Arrays.asList(producers);
        List<Studio> returnList = new ArrayList<>();
        for(String studio : studioList){
            returnList.add(new Studio(UUID.randomUUID(),studio));
        }
        return returnList;
    }
}
