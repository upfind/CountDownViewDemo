package cn.upfinder.countdownviewdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cn.upfinder.countdownview.view.CircleCountDownView;

public class MainActivity extends AppCompatActivity {


    private Context context;
    private CircleCountDownView circleCountDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        circleCountDownView = (CircleCountDownView) findViewById(R.id.ccdv);
        circleCountDownView.setOnFinishListener(new CircleCountDownView.OnFinishListener() {
            @Override
            public void onFinish() {
                Toast.makeText(context, "结束", Toast.LENGTH_SHORT).show();
            }
        });
        circleCountDownView.start();
    }
}
