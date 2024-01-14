package com.teste.apiTeste.infra.dto.request;

import com.teste.apiTeste.domain.entity.FilmAwards;

import java.util.List;

public record ProducerResquest(String name, List<FilmAwards> filmAwards) {
}
