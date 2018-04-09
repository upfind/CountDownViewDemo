package cn.upfinder.countdownviewdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cn.upfinder.countdownview.CircleCountDownView;

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
                Intent intent = new Intent(MainActivity.this, CountDownActivity.class);
                startActivity(intent);

            }
        });
//        circleCountDownView.start();

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circleCountDownView.start();
            }
        });
    }
}
