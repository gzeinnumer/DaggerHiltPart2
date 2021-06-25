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
```kotlin
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
```kotlin
//todo 4
@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule{

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
```

#### Todo 5
```kotlin
//todo 5
interface ApiService {
    @GET("data/2.5/forecast")
    fun getCuaca(
        @Query("id") id: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): Call<ResponseCuaca>
}
```

#### Todo 6
```kotlin
object NetworkModule{

    //todo 6
    @Singleton
    @Provides
    fun apiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    //end todo 6
}
```

#### Todo 7
```kotlin
//todo 7
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity_"

    @Inject lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService.getCuaca("1642907", "03207a5afa25a1f6db2d2fcc6dd63fc1", "metric")
            .enqueue(object : Callback<ResponseCuaca> {
                override fun onFailure(call: Call<ResponseCuaca>, t: Throwable) {
                    Log.d(TAG, "onFailure: "+t.message)
                }

                override fun onResponse(
                    call: Call<ResponseCuaca>,
                    response: Response<ResponseCuaca>
                ) {
                    Log.d(TAG, "onResponse: "+ (response.body()?.list?.size ?: 0))
                }
            })
    }
}
```

---

```
Copyright 2021 M. Fadli Zein
```