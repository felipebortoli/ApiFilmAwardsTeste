package com.teste.apiTeste.infra.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ReportFilmAwardResponse {
    private List<ReportFilmAwardInfoResponse> min;
    private List<ReportFilmAwardInfoResponse> max;
}
