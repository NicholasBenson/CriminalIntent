package com.bignerdranch.android.criminalintent;

/**
 * Created by nbens_000 on 3/14/2016.
 */
public class StockDbSchema {
    public static final class StockTable {
        public static final String NAME = "stocks";

        public static final class Cols {
            public static final String TITLE = "title";
            public static final String UUID = "uuid";
            public static final String WEIGHT = "weight";
            public static final String OVERWEIGHT = "overWeight";
            public static final String UNDERWEIGHT = "underWeight";
            public static final String NEUTRAL = "neutral";
        }
    }
}
