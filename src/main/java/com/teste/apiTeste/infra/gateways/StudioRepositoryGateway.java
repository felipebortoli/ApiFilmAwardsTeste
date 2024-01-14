package com.teste.apiTeste.infra.gateways;

import com.teste.apiTeste.application.gateways.StudioGateway;
import com.teste.apiTeste.domain.entity.Studio;
import com.teste.apiTeste.infra.persistence.StudioEntity;
import com.teste.apiTeste.infra.persistence.StudioRepository;

import java.util.ArrayList;
import java.util.List;

public class StudioRepositoryGateway implements StudioGateway {

    private final StudioRepository studioRepository;

    public StudioRepositoryGateway(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @Override
    public void createManyStudios(List<Studio> studiosDomain) {
        List<StudioEntity> studioEntities = new ArrayList<>();
        for(Studio studio : studiosDomain ){
            StudioEntity entity = new StudioEntity();
            entity.setId(studio.id());
            entity.setName(studio.name());
            studioEntities.add(entity);
        }
        this.studioRepository.saveAll(studioEntities);

    }
}
