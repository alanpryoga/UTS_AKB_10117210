/**
 * Tanggal Pengerjaan   : 11/05/2020
 * NIM                  : 10117210
 * Nama                 : Ade Syahlan Prayoga
 * Kelas                : IF7
 */
package com.github.alanpryoga.tugasutsakb.ui.about;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alanpryoga.tugasutsakb.R;

public class AboutFragment extends Fragment implements AboutContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}
