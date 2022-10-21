package com.example.adatest;

import android.app.Application;
import android.os.Bundle;

import com.yariksoffice.lingver.Lingver;

public class LanguageApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Lingver.init(this);
    }
}
