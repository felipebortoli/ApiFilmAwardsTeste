package com.teste.apiTeste.application.gateways;

import com.teste.apiTeste.domain.entity.Producer;
import com.teste.apiTeste.infra.dto.response.ProducerResponse;
import com.teste.apiTeste.infra.dto.request.ProducerResquest;
import com.teste.apiTeste.infra.persistence.ProducerEntity;

import java.util.List;
import java.util.UUID;

public interface ProducerGateway {

    void createManyCostumers(List<Producer> filmAwardsDomain);

    List<ProducerEntity> findProducersWinnerFilmsAwards();

    List<ProducerResponse> list();

    boolean create(ProducerResquest producerResquest) throws Exception;

    boolean update(ProducerResquest producerResquest, UUID id) throws Exception;

    boolean delete(UUID id) throws Exception;

    ProducerResponse show(UUID id) throws Exception;
}
