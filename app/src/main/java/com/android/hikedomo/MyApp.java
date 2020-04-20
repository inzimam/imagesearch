package com.android.hikedomo;

import android.app.Application;

import com.android.hikedomo.di.component.ApiComponent;
import com.android.hikedomo.di.component.DaggerApiComponent;
import com.android.hikedomo.di.module.AppModule;
import com.android.hikedomo.di.module.ContextModule;
import com.android.hikedomo.di.module.RetrofitApiModule;
import com.android.hikedomo.utils.Constants;

public class MyApp extends Application {
    private ApiComponent apiComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent = DaggerApiComponent.builder().contextModule(new ContextModule(this))
                .appModule(new AppModule(this))
                .retrofitApiModule(new RetrofitApiModule(Constants.BASE_URL))
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}