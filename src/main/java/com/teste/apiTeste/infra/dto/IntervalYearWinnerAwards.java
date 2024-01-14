package com.teste.apiTeste.infra.dto;

import com.teste.apiTeste.infra.persistence.FilmAwardsEntity;
import lombok.Data;

import java.util.Comparator;


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
