package com.teste.apiTeste.application.usecases;

import com.teste.apiTeste.application.gateways.FilmAwardsGateway;
import com.teste.apiTeste.infra.dto.request.FilmAwardsRequest;
import com.teste.apiTeste.infra.dto.request.ProducerResquest;

import java.util.UUID;

public class FilmAwardsInteractor {

    private final FilmAwardsGateway filmAwardsGateway;


    public FilmAwardsInteractor(FilmAwardsGateway filmAwardsGateway) {
        this.filmAwardsGateway = filmAwardsGateway;
    }

    public Object list() {
        return filmAwardsGateway.list();
    }

    public Object show(UUID id) throws Exception {
        return filmAwardsGateway.show(id);
    }

    public Object create(FilmAwardsRequest filmAwardsRequest) throws Exception {
        return filmAwardsGateway.create(filmAwardsRequest);
    }

    public Object update(FilmAwardsRequest filmAwardsRequest, UUID id) throws Exception {
        return filmAwardsGateway.update(filmAwardsRequest,id);
    }

    public Object delete(UUID id) throws Exception {
        return filmAwardsGateway.delete(id);
    }
}
