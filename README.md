# DaggerHiltPart2

#### Todo 1
```gradle
buildscript {
    repositories {
         ...
        //todo 1
        mavenCentral()
    }
    dependencies {
        classpath "com.android.tools.build:gradle:4.0.0"

        //todo 1
        classpath 'com.google.dagger:hilt-android-gradle-plugin:2.28-alpha'
    }
}
```

#### Todo 2
```gradle
//todo 2
apply plugin: 'com.android.application'
apply plugin: 'dagger.hilt.android.plugin'

dependencies {
    ...
    //todo 2
    implementation 'com.google.dagger:hilt-android:2.28-alpha'
    annotationProcessor 'com.google.dagger:hilt-android-compiler:2.28-alpha'
    implementation 'androidx.multidex:multidex:2.0.1'

    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.7.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.7.2'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.4.0'
}
```

#### Todo 3
```java
//todo 3
@HiltAndroidApp
public final class MyApp extends MultiDexApplication {}
```
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest>

    <application
        android:name=".MyApp">

    </application>

</manifest>
```

#### Todo 4
```java
//todo 4
@Module
@InstallIn(ApplicationComponent.class)
public final class AppModule {

    @Provides
    @Singleton
    static Retrofit retrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
```

#### Todo 5
```java
//todo 5
public interface ApiService {

    @GET("data/2.5/forecast")
    Call<ResponseCuaca> getCuaca(
            @Query("id") String id,
            @Query("appid") String appid,
            @Query("units") String units
    );

}
```

#### Todo 6
```java
//todo 6
public final class NetworkModule {

    //todo 6
    @Singleton
    @Provides
    static ApiService apiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
    //end todo 6
}
```

#### Todo 7
```java
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
```

---

```
Copyright 2021 M. Fadli Zein
```