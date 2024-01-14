package com.teste.apiTeste.infra.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.teste.apiTeste.application.usecases.ReportFilmsAwardsInteractor;
import com.teste.apiTeste.infra.Utils.AppResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/report")
public class ReportFilmAwardsController {

    private final ReportFilmsAwardsInteractor reportFilmsAwardsInteractor;

    public ReportFilmAwardsController(ReportFilmsAwardsInteractor reportFilmsAwardsInteractor) {
        this.reportFilmsAwardsInteractor = reportFilmsAwardsInteractor;
    }


    @GetMapping("/list")
    public JsonNode listfilmsAwardsWinners() {
        try {
            return AppResponse.success(reportFilmsAwardsInteractor.getProducerLongestGapBetweenConsecutiveAwards());
        } catch (Exception e) {
            return AppResponse.error(e.getMessage());
        }
    }

}
