package com.gzeinnumer.daggerhiltpart2.network;

import com.gzeinnumer.daggerhiltpart2.network.model.ResponseCuaca;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

//todo 5
public interface ApiService {

    @GET("data/2.5/forecast")
    Call<ResponseCuaca> getCuaca(
            @Query("id") String id,
            @Query("appid") String appid,
            @Query("units") String units
    );

}
