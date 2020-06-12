package com.gzeinnumer.daggerhiltpart2.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.gzeinnumer.daggerhiltpart2.R;
import com.gzeinnumer.daggerhiltpart2.network.ApiService;
import com.gzeinnumer.daggerhiltpart2.network.model.ResponseCuaca;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//todo 7
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService.getCuaca("1642907", "03207a5afa25a1f6db2d2fcc6dd63fc1", "metric").enqueue(new Callback<ResponseCuaca>() {
            @Override
            public void onResponse(Call<ResponseCuaca> call, Response<ResponseCuaca> response) {
                Log.d(TAG, "onResponse: "+response.body().getList().size());
            }

            @Override
            public void onFailure(Call<ResponseCuaca> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}