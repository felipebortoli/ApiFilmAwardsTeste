package com.teste.apiTeste.infra.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.teste.apiTeste.application.usecases.ProducerInteractor;
import com.teste.apiTeste.infra.Utils.AppResponse;
import com.teste.apiTeste.infra.dto.request.ProducerResquest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/producer")
public class ProducersController {

    private final ProducerInteractor producerInteractor;

    public ProducersController(ProducerInteractor producerInteractor) {
        this.producerInteractor = producerInteractor;
    }


    @GetMapping("/list")
    public JsonNode list() {
        try {
            return AppResponse.success(producerInteractor.list());
        } catch (Exception e) {
            return AppResponse.error(e.getMessage());
        }
    }

    @GetMapping("/show/{id}")
    public JsonNode show(@PathVariable UUID id) {
        try {
            return AppResponse.success(producerInteractor.show(id));
        } catch (Exception e) {
            return AppResponse.error(e.getMessage());
        }
    }

    @PostMapping("/create")
    public JsonNode create(@RequestBody ProducerResquest producerResquest) {
        try {
            return AppResponse.success(producerInteractor.create(producerResquest));
        } catch (Exception e) {
            return AppResponse.error(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public JsonNode update(@RequestBody ProducerResquest producerResquest, @PathVariable UUID id) {
        try {
            return AppResponse.success(producerInteractor.update(producerResquest, id));
        } catch (Exception e) {
            return AppResponse.error(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonNode delete(@PathVariable UUID id) {
        try {
            return AppResponse.success(producerInteractor.delete(id));
        } catch (Exception e) {
            return AppResponse.error(e.getMessage());
        }
    }

}
