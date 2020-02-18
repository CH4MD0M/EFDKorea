package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class SubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_subject);


        Intent intent1 = getIntent();
        //GradeActivity의 GradeNumber를 받아와
        //ListActivity로 넘겨줄 P(Pass)_GradeNumber에 저장한다.
        final String P_GradeNumber = intent1.getStringExtra("GradeNumber");



        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Button button1 = (Button)findViewById(R.id.button1);
                Intent intent = new Intent(
                        getApplicationContext(),
                        ListActivity.class);
                //ListActivity로 넘겨줄 SubjectNumber를 생성
                intent.putExtra("SubjectNumber",button1.getText().toString());
                //P_GradeNumber를 ListActivity로 넘겨줄  R(Receive)_GradeNumber 생성
                intent.putExtra("R_GradeNumber",P_GradeNumber);
                startActivity(intent);
            }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Button button2 = (Button)findViewById(R.id.button2);
                Intent intent = new Intent(
                        getApplicationContext(),
                        ListActivity.class);
                intent.putExtra("SubjectNumber",button2.getText().toString());
                intent.putExtra("R_GradeNumber",P_GradeNumber);
                startActivity(intent);
            }
        });

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Button button3 = (Button)findViewById(R.id.button3);
                Intent intent = new Intent(
                        getApplicationContext(),
                        ListActivity.class);
                intent.putExtra("SubjectNumber",button3.getText().toString());
                intent.putExtra("R_GradeNumber",P_GradeNumber);
                startActivity(intent);
            }
        });
    }
}
