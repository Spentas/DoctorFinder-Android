package com.spentas.javad.doctorfinder.network;


import com.spentas.javad.doctorfinder.app.App;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHelper {

    @Inject
    OkHttpClient mOkHttpClient;

    public HttpHelper() {
        ((App) App.getContext()).getComponent().inject(this);
    }


    public void getData(String endPoint, final HttpCallback callback) throws Exception {

        Request request = new Request.Builder()
                .url(NetworkConfig.BASE_URL + endPoint)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                callback.httpCallback(response.body().string());

            }
        });
    }
    public void getDataById(int id, final HttpCallback callback) throws Exception {

        Request request = new Request.Builder()
                .url(String.format(NetworkConfig.BASE_URL+NetworkConfig.PROFILE_ENDPOINT,id))
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                callback.httpCallback(response.body().string());

            }
        });
    }


}

