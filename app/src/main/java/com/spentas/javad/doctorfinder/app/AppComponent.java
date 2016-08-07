package com.spentas.javad.doctorfinder.app;


import com.spentas.javad.doctorfinder.BaseActivity;
import com.spentas.javad.doctorfinder.fragment.DrDetailFragment;
import com.spentas.javad.doctorfinder.network.HttpHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})

public interface AppComponent {
        void inject(App application);
        void inject(BaseActivity activity);
        void inject(HttpHelper httpHelper);
        void inject(DrDetailFragment drDetailFragment);
}