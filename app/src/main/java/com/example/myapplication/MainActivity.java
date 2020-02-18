package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 타이틀바 없애기
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String GradeNumber = intent.getStringExtra("Grade");
        final String SubjectNumber = intent.getStringExtra("Subject");
        final String MediaNumber = intent.getStringExtra("MediaNumber");

        TextView textView = (TextView)findViewById(R.id.textView1);
        textView.setText("/storage/sdcard0/"+GradeNumber+"/"+SubjectNumber+"/"+MediaNumber+".mp4");
    }
}
