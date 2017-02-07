package com.example.rlard008.prototype2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {


    //check if app running

    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        isRunning = true;
        startSplash();

    }

    // Starts the count down timer for 3-seconds. It simply sleeps the thread
    // for 3-seconds.

    void startSplash() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            doFinish();
                        }
                    });
                }
            }
        }).start();

    }

    // start the Login activity and finish the Splash.
    void doFinish() {

        if(isRunning) {

            Intent splash = new Intent(SplashScreen.this,LoginActivity.class);
            startActivity(splash);
            finish();
        }


    }
}
