package com.teste.apiTeste.infra.dto.request;

import com.teste.apiTeste.infra.persistence.ProducerEntity;
import com.teste.apiTeste.infra.persistence.StudioEntity;

import java.util.List;
import java.util.UUID;

public record FilmAwardsRequest(String title, int year, List<ProducerEntity> producers, List<StudioEntity> studios, boolean winner) {
}
