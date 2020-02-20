package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EnglishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng);

        Button button1 = (Button) findViewById(R.id.grade1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button1 = (Button) findViewById(R.id.grade1);


                Intent intent = new Intent(getApplicationContext(), EngSubjectActivity.class);
                intent.putExtra("GradeNumber", button1.getText().toString());
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.grade2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button2 = (Button) findViewById(R.id.grade2);

                Intent intent = new Intent(getApplicationContext(), EngSubjectActivity.class);
                intent.putExtra("GradeNumber", button2.getText().toString());
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.grade3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button3 = (Button) findViewById(R.id.grade3);

                Intent intent = new Intent(getApplicationContext(), EngSubjectActivity.class);
                intent.putExtra("GradeNumber", button3.getText().toString());
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.grade4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button4 = (Button) findViewById(R.id.grade4);
//Update later
//                Intent intent = new Intent(getApplicationContext(), SubjectActivity.class);
//                intent.putExtra("GradeNumber", button4.getText().toString());
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Sorry, Preparing the service!!",Toast.LENGTH_SHORT).show();
            }
        });

        Button button5 = (Button) findViewById(R.id.grade5);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button5 = (Button) findViewById(R.id.grade5);

//Update later
//                Intent intent = new Intent(getApplicationContext(), SubjectActivity.class);
//                intent.putExtra("GradeNumber", button5.getText().toString());
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Sorry, Preparing the service!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
