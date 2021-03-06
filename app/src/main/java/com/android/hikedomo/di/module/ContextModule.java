package com.android.hikedomo.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context mContext;

    public ContextModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext.getApplicationContext();
    }
}
