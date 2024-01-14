package com.teste.apiTeste.domain.entity;

import java.util.List;

public record FilmAwards(String year, String title, String winner, List<Producer> producers , List<Studio> studios ) {

}
