/**
 * Tanggal Pengerjaan   : 11/05/2020
 * NIM                  : 10117210
 * Nama                 : Ade Syahlan Prayoga
 * Kelas                : IF7
 */
package com.github.alanpryoga.tugasutsakb.presenter;

import com.github.alanpryoga.tugasutsakb.ui.home.HomeContract;

public class HomePresenterImpl implements HomeContract.Presenter {

    private HomeContract.View view;

    public HomePresenterImpl(HomeContract.View view) {
        this.view = view;
    }
}
