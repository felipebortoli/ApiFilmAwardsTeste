package com.teste.apiTeste.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudioRepository  extends JpaRepository<StudioEntity, UUID> {
}
