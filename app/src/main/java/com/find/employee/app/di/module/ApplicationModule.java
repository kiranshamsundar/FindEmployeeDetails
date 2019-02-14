package com.find.employee.app.di.module;

import com.find.employee.app.data.rest.EmployeeService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module(includes = ViewModelModule.class)
public class ApplicationModule {

//    private static final String BASE_URL = "https://dl.dropboxusercontent.com/";

    //private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static final String BASE_URL = "https://demo9919266.mockable.io/";


    @Singleton
    @Provides
    OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(logging);
        return client.build();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static EmployeeService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(EmployeeService.class);
    }
}
