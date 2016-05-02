package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.util.UUID;


/**
 * Created by nbens_000 on 2/23/2016.
 */
public class StockFragment extends android.support.v4.app.Fragment{

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String ZOOMED_IMAGE = "ZoomedImage";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_PHOTO= 2;

    private Stock mStock;
    private File mPhotoFile;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mReportButton;
    private Button mSuspectButton;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private Spinner mSpinner;


    public static StockFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        StockFragment fragment = new StockFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mStock = StockLab.get(getActivity()).getStock(crimeId);
        //mStock.setNeutral(true);
        //mPhotoFile = StockLab.get(getActivity()).getPhotoFile(mStock);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause(){
        super.onPause();

        StockLab.get(getActivity())
                .updateStock(mStock);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_stock, container, false);

        mTitleField = (EditText)v.findViewById(R.id.stock_title);
        mTitleField.setText(mStock.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStock.setTitle(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSpinner = (Spinner)v.findViewById(R.id.stock_spinner);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                mStock.setWeight(position);
                StockLab.get(getActivity()).updateStock(mStock);
            }
                /*if (parent.getSelectedItem().toString().equals("OverWeight")){
                    mStock.setOverWeight(true);
                    //parent.setSelection(0);
                }else if (parent.getSelectedItem().toString().equals("UnderWeight")){
                    mStock.setUnderWeight(true);
                }else if (parent.getSelectedItem().toString().equals("Neutral")){
                    mStock.setNeutral(true);
                }*/


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });



        /*mDateButton = (Button)v.findViewById(R.id.crime_date);
        updateDate(setLongFormat(mStock.getDate()).toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mStock.getDate());
                dialog.setTargetFragment(StockFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });*/

        /*mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mStock.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Set the crime's solved property
                mStock.setSolved(isChecked);
            }
        });*/

        /*mReportButton = (Button) v.findViewById(R.id.crime_report);
        mReportButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
                i.putExtra(Intent.EXTRA_SUBJECT,
                        getString(R.string.crime_report_subject));
                i = Intent.createChooser(i, getString(R.string.send_report));
                startActivity(i);
            }
        });*/

        //final Intent pickContact = new Intent(Intent.ACTION_PICK,
          //      ContactsContract.Contacts.CONTENT_URI);
        //pickContact.addCategory(Intent.CATEGORY_HOME);
        /*mSuspectButton = (Button) v.findViewById(R.id.crime_suspect);
        mSuspectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivityForResult(pickContact, REQUEST_CONTACT);
            }
        });

        if (mStock.getSuspect() != null) {
            mSuspectButton.setText(mStock.getSuspect());
        }*/

        /*PackageManager packageManager = getActivity().getPackageManager();
        if (packageManager.resolveActivity(pickContact,
                PackageManager.MATCH_DEFAULT_ONLY) == null) {
            mSuspectButton.setEnabled(false);
        }*/

        /*mPhotoButton = (ImageButton) v.findViewById(R.id.crime_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);

        if (canTakePhoto){
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        mPhotoView = (ImageView) v.findViewById(R.id.crime_photo);

        ViewTreeObserver observer = mPhotoView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                updatePhotoView();
                mPhotoView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        //updatePhotoView();
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPhotoFile != null && mPhotoFile.exists()) {
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    ZoomedFragment dialog = ZoomedFragment.newInstance(mPhotoFile);
                    dialog.show(manager, ZOOMED_IMAGE);
                }
            }
        });*/

        return v;
    }


    /*private void updateDate(String text) {
        mDateButton.setText(text);
    }

    private String getCrimeReport(){
        String solvedString = null;
        if (mStock.isSolved()) {
            solvedString = getString(R.string.crime_report_solved);
        }else{
            solvedString = getString(R.string.crime_report_unsolved);
        }

        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat, mStock.getDate()).toString();

        String suspect = mStock.getSuspect();
        if (suspect == null){
            suspect = getString(R.string.crime_report_no_suspect);
        }else{
            suspect = getString(R.string.crime_report_suspect, suspect);
        }

        String report = getString(R.string.crime_report,
                mStock.getTitle(), dateString, solvedString, suspect);

        return report;
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }*/

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_DATE){
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mStock.setDate(date);
            updateDate(mStock.getDate().toString());
        } else if (requestCode == REQUEST_CONTACT && data != null){
            Uri contactURI = data.getData();
            // Specify which fields you want your query to return values for
            String[] queryFields = new String[] {
                    ContactsContract.Contacts.DISPLAY_NAME
            };
            // perform your query - the contactUri is like a "where"
            // clause here
            Cursor c = getActivity().getContentResolver()
                    .query(contactURI, queryFields, null, null, null);
            try {
                //Double-check that you actually got results
                if (c.getCount() == 0) {
                    return;
                }

                //Pull out the first column of the first row of data -
                //that is your suspects name.
                c.moveToFirst();
                String suspect = c.getString(0);
                mStock.setSuspect(suspect);
                mSuspectButton.setText(suspect);
            }finally{
                c.close();
            }
        } else if (requestCode == REQUEST_PHOTO) {
            updatePhotoView();
        }
    }

    private String setLongFormat (Date date){
        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL);
        return dateFormat.format(date);
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_stock_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_delete_stock:
                StockLab.get(getActivity()).deleteStock(mStock);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
