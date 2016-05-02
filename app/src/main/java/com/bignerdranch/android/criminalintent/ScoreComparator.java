package com.bignerdranch.android.criminalintent;

import java.util.Comparator;

/**
 * Created by nbens_000 on 5/2/2016.
 */
public class ScoreComparator implements Comparator<ExchangeRatios> {
    public int compare(ExchangeRatios VC1, ExchangeRatios VC2){
        //System.out.println((int)(VC1.getScore() - VC2.getScore()));
        return (int)(VC1.getScore() - VC2.getScore());
    }
}
