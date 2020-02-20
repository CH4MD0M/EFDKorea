package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        Button button1 = (Button) findViewById(R.id.language1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button1 = (Button) findViewById(R.id.language1);


                Intent intent = new Intent(getApplicationContext(), KoreanActivity.class);
                intent.putExtra("Language", button1.getText().toString());
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.language2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button2 = (Button) findViewById(R.id.language2);

                Intent intent = new Intent(getApplicationContext(), EnglishActivity.class);
                intent.putExtra("Language", button2.getText().toString());
                startActivity(intent);
            }
        });



    }
}
