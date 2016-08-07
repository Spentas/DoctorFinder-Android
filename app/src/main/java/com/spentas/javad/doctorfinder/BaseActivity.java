package com.spentas.javad.doctorfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.spentas.javad.doctorfinder.app.App;
import com.spentas.javad.doctorfinder.network.HttpCallback;
import com.spentas.javad.doctorfinder.network.HttpHelper;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * com.spentas.javad.doctorfinder.BaseActivity
 */
public class BaseActivity extends AppCompatActivity implements HttpCallback {


    @Inject
    HttpHelper mHttpHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getComponent().inject(this);
    }


    @Override
    public void httpCallback(String response) {

    }
}
