package com.teste.apiTeste.application.usecases;

import com.teste.apiTeste.application.gateways.StudioGateway;
import com.teste.apiTeste.domain.entity.Producer;
import com.teste.apiTeste.domain.entity.Studio;

import java.util.List;

public class StudioInteractor {

    private final StudioGateway studioGateway;

    public StudioInteractor(StudioGateway studioGateway) {
        this.studioGateway = studioGateway;
    }

    public void saveAllStudiosEntity(List<Studio> studios){
        studioGateway.createManyStudios(studios);
    }
}
