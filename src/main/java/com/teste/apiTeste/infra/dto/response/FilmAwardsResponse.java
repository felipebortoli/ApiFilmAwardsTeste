package com.teste.apiTeste.infra.dto.response;

import com.teste.apiTeste.domain.entity.Producer;
import com.teste.apiTeste.domain.entity.Studio;
import com.teste.apiTeste.infra.persistence.ProducerEntity;
import com.teste.apiTeste.infra.persistence.StudioEntity;

import java.util.List;
import java.util.UUID;

public record FilmAwardsResponse(UUID id, String title, int year, List<Producer> producers, List<Studio> studios, boolean winner) {
}
