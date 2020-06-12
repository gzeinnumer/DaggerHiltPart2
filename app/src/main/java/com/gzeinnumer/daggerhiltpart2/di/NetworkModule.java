package com.gzeinnumer.daggerhiltpart2.di;

import com.gzeinnumer.daggerhiltpart2.network.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//todo 4
@Module
@InstallIn(ApplicationComponent.class)
public final class NetworkModule {

    @Provides
    @Singleton
    static Retrofit retrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //todo 6
    @Singleton
    @Provides
    static ApiService apiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
    //end todo 6
}
