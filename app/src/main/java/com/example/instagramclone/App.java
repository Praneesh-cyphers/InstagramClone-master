package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("OtiJSJy9FYFsSel5DHIDr8UwB6zjRI9zya8WUl7H")
                .clientKey("j3g7LR2NDv3tJIrIjimRQ5w51WuPAVdbTYloJVjl")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
