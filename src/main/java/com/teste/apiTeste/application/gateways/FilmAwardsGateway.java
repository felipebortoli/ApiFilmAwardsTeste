package com.teste.apiTeste.application.gateways;

import com.teste.apiTeste.domain.entity.FilmAwards;
import com.teste.apiTeste.infra.dto.request.FilmAwardsRequest;
import com.teste.apiTeste.infra.dto.request.ProducerResquest;
import com.teste.apiTeste.infra.dto.response.FilmAwardsResponse;
import com.teste.apiTeste.infra.dto.response.ProducerResponse;


import java.util.List;
import java.util.UUID;

public interface FilmAwardsGateway {

    void createManyFilmsAward(List<FilmAwards> filmAwardsDomain);

    List<FilmAwardsResponse> list();

    boolean create(FilmAwardsRequest filmAwardsRequest) throws Exception;

    boolean update(FilmAwardsRequest filmAwardsRequest, UUID id) throws Exception;

    boolean delete(UUID id) throws Exception;

    FilmAwardsResponse show(UUID id) throws Exception;

}
