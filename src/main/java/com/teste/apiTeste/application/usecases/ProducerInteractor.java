package com.teste.apiTeste.application.usecases;

import com.teste.apiTeste.application.gateways.ProducerGateway;

import com.teste.apiTeste.infra.dto.request.ProducerResquest;

import java.util.UUID;

public class ProducerInteractor {

    private final ProducerGateway producerGateway;

    public ProducerInteractor(ProducerGateway producerGateway) {
        this.producerGateway = producerGateway;
    }

    public Object list() {
        return producerGateway.list();
    }

    public Object show(UUID id) throws Exception {
        return producerGateway.show(id);
    }

    public Object create(ProducerResquest producerResquest) throws Exception {
        return producerGateway.create(producerResquest);
    }

    public Object update(ProducerResquest producerResquest, UUID id) throws Exception {
        return producerGateway.update(producerResquest,id);
    }

    public Object delete(UUID id) throws Exception {
        return producerGateway.delete(id);
    }
}
