package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nbens_000 on 3/1/2016.
 */
public class StockListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mStockRecyclerView;
    private StockAdapter mAdapter;
    private boolean mSubtitleVisible;
    private TextView mWelcomeView;
    private Button mAddStock;
    private List<Stock> mStocks;
    private int[] mWeights;

    private static final String SPIN_KEY = "spinner";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        StockLab stockLab = StockLab.get(getActivity());
        int stockCount = stockLab.getStocks().size();

        View view = inflater.inflate(R.layout.fragment_stock_list, container, false);
        mWelcomeView = (TextView) view.findViewById(R.id.empty_view);
        mAddStock = (Button) view.findViewById(R.id.first_thing);
        mAddStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stock stock = new Stock();
                StockLab.get(getActivity()).addStock(stock);
                Intent intent = StockPagerActivity
                        .newIntent(getActivity(), stock.getId());
                startActivity(intent);
            }
        });

        mStockRecyclerView = (RecyclerView) view.findViewById(R.id.stock_recycler_view);
        mStockRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
            mWeights = savedInstanceState.getIntArray(SPIN_KEY);
        }

        if (stockCount == 0) {
            mStockRecyclerView.setVisibility(View.GONE);
            mWelcomeView.setVisibility(View.VISIBLE);
            mAddStock.setVisibility(View.VISIBLE);
        }
        else {
            mStockRecyclerView.setVisibility(View.VISIBLE);
            mWelcomeView.setVisibility(View.GONE);
            mAddStock.setVisibility(View.GONE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
        //mStocks = StockLab.get(getActivity()).getStocks();
        int[] spinners = new int[mStocks.size()];
        for (int i = 0; i < mStocks.size(); i++) {
            spinners[i] = (mStocks.get(i).getWeight());
        }
        outState.putIntArray(SPIN_KEY, spinners);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_stock_list, menu);

        MenuItem subTitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible) {
            subTitleItem.setTitle(R.string.hide_subtitle);
        }else{
            subTitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_stock:
                Stock stock = new Stock();
                StockLab.get(getActivity()).addStock(stock);
                Intent intent = StockPagerActivity
                        .newIntent(getActivity(), stock.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        StockLab stockLab = StockLab.get(getActivity());
        int stockCount = stockLab.getStocks().size();
        String subtitle = getResources()
                .getQuantityString(R.plurals.subtitle_plural, stockCount, stockCount);

        if (!mSubtitleVisible){
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI(){
        StockLab stockLab = StockLab.get(getActivity());
        mStocks = stockLab.getStocks();

        if (mAdapter == null) {
            mAdapter = new StockAdapter(mStocks);
            mStockRecyclerView.setAdapter(mAdapter);
            if (mWeights != null){
                for (int i = 0; i < mStocks.size()-1; i++){
                    mStocks.get(i).setWeight(mWeights[i]);
                }
            }
        }else{
            mAdapter.setStocks(mStocks);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class StockHolder extends RecyclerView.ViewHolder
            /*implements View.OnClickListener*/ {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Spinner mSpinner;
        //private EditText mEditText;
        private Stock mStock;


        public StockHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);

            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_stock_title_text_view);
            /*mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_stock_date_text_view);
            mSolvedCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_stock_solved_check_box);*/
            mSpinner = (Spinner)
                    itemView.findViewById(R.id.list_item_spinner);
            /*if (mStock.isOverWeight()) {
                mSpinner.setSelection(0);
            } else if (mStock.isNeutral()) {
                mSpinner.setSelection(1);
            } else if (mStock.isUnderWeight()) {
                mSpinner.setSelection(2);
            }*/
            /*mSpinner.setSelection(mStock.getWeight());
            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                    Object item = parent.getItemAtPosition(pos);
                    mStock.setWeight(pos);
                    mStock.setOverWeight(mStock.isOverWeight());
                    mStock.setNeutral(mStock.isNeutral());
                    mStock.setUnderWeight(mStock.isUnderWeight());
                    StockLab.get(getActivity()).updateStock(mStock);
                }

                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });*/

        }

            /*mStock.setOverWeight(mStock.isOverWeight());
            mStock.setNeutral(mStock.isNeutral());
            mStock.setUnderWeight(mStock.isUnderWeight());*/


            /*mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (parent.getSelectedItem().toString().equalsIgnoreCase("OverWeight")) {
                        mStock.setOverWeight(true);
                    } else if (parent.getSelectedItem().toString().equalsIgnoreCase("UnderWeight")) {
                        mStock.setUnderWeight(true);
                    } else if (parent.getSelectedItem().toString().equalsIgnoreCase("Neutral")) {
                        mStock.setNeutral(true);
                    }
                }



                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            /*mEditText = (EditText)
                    itemView.findViewById(R.id.list_item_edit);*/



        //}


        public void bindStock(Stock stock){
            mStock = stock;

            //mEditText.setText(mStock.getTitle());


            mTitleTextView.setText(mStock.getTitle());
            mSpinner.setSelection(mStock.getWeight());

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                    Object item = parent.getItemAtPosition(pos);
                    mStock.setWeight(pos);
                    /*mStock.setOverWeight(mStock.isOverWeight());
                    mStock.setNeutral(mStock.isNeutral());
                    mStock.setUnderWeight(mStock.isUnderWeight());*/
                    StockLab.get(getActivity()).updateStock(mStock);
                }

                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });
            //mDateTextView.setText(mStock.getDate().toString());
            /*mSolvedCheckBox.setChecked(mStock.isSolved());
            mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Set the stock's solved property
                    mStock.setSolved(isChecked);
                }
            });*/


        }

        /*@Override
        public void onClick(View v){
            Intent intent = StockPagerActivity.newIntent(getActivity(), mStock.getId());
            startActivity(intent);
        }*/
    }

    private class StockAdapter extends RecyclerView.Adapter<StockHolder> {

        private List<Stock> mStocks;

        public StockAdapter(List<Stock> stocks){
            mStocks = stocks;
        }


        @Override
        public StockHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_stock, parent, false);


            return new StockHolder(view);
        }

        @Override
        public void onBindViewHolder(StockHolder holder, int position) {
            Stock stock = mStocks.get(position);
            holder.bindStock(stock);
        }

        @Override
        public int getItemCount() {
            return mStocks.size();
        }

        public void setStocks(List<Stock> stocks){
            mStocks = stocks;
        }
    }

}
