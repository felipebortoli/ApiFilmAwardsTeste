package com.teste.apiTeste.application.gateways;

import com.teste.apiTeste.domain.entity.Studio;

import java.util.List;

public interface StudioGateway {

    void createManyStudios(List<Studio> studiosDomain);
}
