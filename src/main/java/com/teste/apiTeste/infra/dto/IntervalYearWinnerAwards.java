package com.teste.apiTeste.infra.dto;

import lombok.Data;


@Data
public class IntervalYearWinnerAwards {

    private String name;
    private int firstYear;
    private int lastYear;
    private int interval;

    public void setIntervalYearsHighestValue(){
        setInterval(lastYear - firstYear);
    }

}
