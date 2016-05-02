package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by nbens_000 on 3/1/2016.
 */
public class StockListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new StockListFragment();
    }
}
