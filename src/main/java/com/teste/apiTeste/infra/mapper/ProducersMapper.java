package com.teste.apiTeste.infra.mapper;

import com.teste.apiTeste.domain.entity.Producer;
import com.teste.apiTeste.infra.dto.response.ProducerResponse;
import com.teste.apiTeste.infra.persistence.ProducerEntity;

import java.util.*;

public class ProducersMapper {

    public List<ProducerEntity> toEntity(List<Producer> producersDomain){
        List<ProducerEntity> returnList = new ArrayList<>();
        for(Producer producer : producersDomain){
            ProducerEntity producerEntity = new ProducerEntity();
            producerEntity.setId(producer.id());
            producerEntity.setName(producer.name());
            returnList.add(producerEntity);
        }
        return returnList;
    }

    public List<Producer> toDomainObj(String[] producers){
        List<String> studioList = Arrays.asList(producers);
        List<Producer> returnList = new ArrayList<>();
        for(String nameStudio : studioList){
            returnList.add(new Producer(UUID.randomUUID(),nameStudio));
        }
        return returnList;
    }

    public List<ProducerResponse> toResponseList(List<ProducerEntity> entities){
        List<ProducerResponse> returnList = new ArrayList<>();
        for (ProducerEntity entity: entities){
            returnList.add(new ProducerResponse(entity.getId(), entity.getName()));
        }
        return returnList;
    }

    public ProducerResponse toResponse(ProducerEntity entity){
        return new ProducerResponse(entity.getId(), entity.getName());
    }

}
