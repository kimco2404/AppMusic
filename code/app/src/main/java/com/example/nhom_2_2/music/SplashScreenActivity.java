package com.example.nhom_2_2.music;
/**
 * Created by kimcodev on 3/7/2017.
 */
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashScreenActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        final ImageView iv = (ImageView) findViewById(R.id.imageView2);
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        final Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animfadeout);
        iv.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(animation2);
                Intent intent = new Intent(getApplicationContext(),MainLayoutActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
