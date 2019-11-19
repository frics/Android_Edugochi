package kr.ac.ssu.edugochi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kr.ac.ssu.edugochi.R;

public class SplashActivity extends AppCompatActivity {

    /* 스플래시 화면이 표시되는 시간을 설정(ms) */
    private final int SPLASH_DISPLAY_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView character = findViewById(R.id.character);
        Glide.with(this).load(R.drawable.character).into(character);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(new Intent(getApplication(), MainActivity.class));
                /* 스플래시 액티비티를 스택에서 제거. */
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }

    @Override
    public void onBackPressed() {
        /* 스플래시 화면에서 뒤로가기 기능 제거. */
    }
}