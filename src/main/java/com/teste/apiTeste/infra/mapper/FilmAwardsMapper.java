package com.teste.apiTeste.infra.mapper;

import com.teste.apiTeste.domain.entity.FilmAwards;
import com.teste.apiTeste.domain.entity.Producer;
import com.teste.apiTeste.domain.entity.Studio;
import com.teste.apiTeste.infra.dto.response.FilmAwardsResponse;
import com.teste.apiTeste.infra.persistence.FilmAwardsEntity;
import com.teste.apiTeste.infra.persistence.ProducerEntity;
import com.teste.apiTeste.infra.persistence.StudioEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FilmAwardsMapper {

    public FilmAwardsEntity toEntity(FilmAwards filmAwardsDomain){
        FilmAwardsEntity filmAwardsEntity = new FilmAwardsEntity();
        filmAwardsEntity.setId(UUID.randomUUID());
        filmAwardsEntity.setTitle(filmAwardsDomain.title());
        filmAwardsEntity.setYear(Integer.parseInt(filmAwardsDomain.year()));
        boolean winner = filmAwardsDomain.winner().equalsIgnoreCase("yes");
        filmAwardsEntity.setWinner(winner);
        return filmAwardsEntity;
    }

    public List<FilmAwardsResponse> toResponseList(List<FilmAwardsEntity> entities){
        List<FilmAwardsResponse> returnList = new ArrayList<>();
        for (FilmAwardsEntity entity: entities){
            List<Producer> producers = getProducers(entity.getProducers());
            List<Studio > studios = getStudios(entity.getStudios());
            returnList.add(new FilmAwardsResponse(entity.getId(),entity.getTitle(), entity.getYear(), producers,studios,entity.isWinner()));
        }
        return returnList;
    }

    private List<Studio> getStudios(List<StudioEntity> studios) {
        List<Studio> studiosDomain = new ArrayList<>();
        for(StudioEntity entity: studios){
            studiosDomain.add(new Studio(entity.getId(),entity.getName()));
        }
        return studiosDomain;
    }

    private List<Producer> getProducers(List<ProducerEntity> producers) {
        List<Producer> producersDomain = new ArrayList<>();
        for(ProducerEntity producer: producers){
            producersDomain.add(new Producer(producer.getId(),producer.getName()));
        }
        return producersDomain;
    }


    public FilmAwardsResponse toResponse(FilmAwardsEntity filmAwardsEntity) {
        return new FilmAwardsResponse(filmAwardsEntity.getId(),filmAwardsEntity.getTitle(),filmAwardsEntity.getYear(),getProducers(filmAwardsEntity.getProducers()),getStudios(filmAwardsEntity.getStudios()),filmAwardsEntity.isWinner());
    }
}
