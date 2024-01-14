package com.teste.apiTeste.infra.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.teste.apiTeste.application.usecases.FilmAwardsInteractor;
import com.teste.apiTeste.infra.Utils.AppResponse;
import com.teste.apiTeste.infra.dto.request.FilmAwardsRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/filmaward")
public class FilmAwardsController {

    private final FilmAwardsInteractor filmAwardsInteractor;

    public FilmAwardsController(FilmAwardsInteractor filmAwardsInteractor) {
        this.filmAwardsInteractor = filmAwardsInteractor;
    }

    @GetMapping("/list")
    public JsonNode list() {
        try {
            return AppResponse.success(filmAwardsInteractor.list());
        } catch (Exception e) {
            return AppResponse.error(e.getMessage());
        }
    }

    @GetMapping("/show/{id}")
    public JsonNode show(@PathVariable UUID id) {
        try {
            return AppResponse.success(filmAwardsInteractor.show(id));
        } catch (Exception e) {
            return AppResponse.error(e.getMessage());
        }
    }

    @PostMapping("/create")
    public JsonNode create(@RequestBody FilmAwardsRequest filmAwardsRequest) {
        try {
            return AppResponse.success(filmAwardsInteractor.create(filmAwardsRequest));
        } catch (Exception e) {
            return AppResponse.error(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public JsonNode update(@RequestBody FilmAwardsRequest filmAwardsRequest, @PathVariable UUID id) {
        try {
            return AppResponse.success(filmAwardsInteractor.update(filmAwardsRequest, id));
        } catch (Exception e) {
            return AppResponse.error(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonNode delete(@PathVariable UUID id) {
        try {
            return AppResponse.success(filmAwardsInteractor.delete(id));
        } catch (Exception e) {
            return AppResponse.error(e.getMessage());
        }
    }
}
