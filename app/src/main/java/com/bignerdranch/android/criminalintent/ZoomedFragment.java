package com.bignerdranch.android.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by nbens_000 on 4/1/2016.
 */
public class ZoomedFragment extends DialogFragment{
    private static final String ARG_ZOOM = "image";

    public static ZoomedFragment newInstance(File crimeImg){
        Bundle args = new Bundle();
        args.putSerializable(ARG_ZOOM, crimeImg);
        ZoomedFragment fragment = new ZoomedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        super.onCreateDialog(savedInstanceState);

        File zoomFile = (File) getArguments().getSerializable(ARG_ZOOM);
        Bitmap imageZoom = PictureUtils.getScaledBitmap(zoomFile.getPath(), getActivity());
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.zoom_image, null);

        ImageView mImageView = (ImageView) v.findViewById(R.id.zoom_image_view);
        mImageView.setImageBitmap(imageZoom);

        return new AlertDialog.Builder(getActivity())
                .setPositiveButton(android.R.string.ok, null)
                .setView(mImageView)
                .create();
    }
}
