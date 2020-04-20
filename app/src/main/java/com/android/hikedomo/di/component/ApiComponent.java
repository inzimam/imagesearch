package com.android.hikedomo.di.component;

import com.android.hikedomo.di.module.AppModule;
import com.android.hikedomo.di.module.ContextModule;
import com.android.hikedomo.di.module.PicassoModule;
import com.android.hikedomo.di.module.RetrofitApiModule;
import com.android.hikedomo.fragment.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitApiModule.class, AppModule.class, PicassoModule.class, ContextModule.class})
public interface ApiComponent {

    void inject(SearchFragment searchFragment);
}
