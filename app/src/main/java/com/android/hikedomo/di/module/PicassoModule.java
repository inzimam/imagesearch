package com.android.hikedomo.di.module;


import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PicassoModule {

    @Singleton
    @Provides
    public Picasso providePicasso(Context context) {
        return new Picasso.Builder(context).build();
    }
}
