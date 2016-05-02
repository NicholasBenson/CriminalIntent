package com.bignerdranch.android.criminalintent;

/**
 * Created by nbens_000 on 5/2/2016.
 */
public class ExchangeRatios {
    private String over;
    private String under;
    private Double score;


    public ExchangeRatios(String over, String under, Double score){
        this.over = over;
        this.under = under;
        this.score = score;
    }

    public Double getScore(){
        //System.out.println(score);
        return score;
    }
}

