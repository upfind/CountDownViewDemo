package cn.upfinder.countdownviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.iwgang.countdownview.CountdownView;

public class CountDownActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        CountdownView mCvCountdownViewTest2 = findViewById(R.id.cv_countdownViewTest1);
        mCvCountdownViewTest2.setTag("test2");
        long time2 = (long) 30 * 60 * 1000;
        mCvCountdownViewTest2.start(time2);
    }
}
