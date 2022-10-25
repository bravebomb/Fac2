package com.example.adatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FAQActivity extends AppCompatActivity {

    TextView question1;
    TextView question2;
    TextView question3;
    TextView question4;

    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView answer4;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        question4 = findViewById(R.id.question4);

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        layout = findViewById(R.id.layoutfaq);

        question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer1.setVisibility(View.VISIBLE);
                layout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        answer1.setVisibility(view.GONE);
                        answer2.setVisibility(view.GONE);
                        answer3.setVisibility(view.GONE);
                        answer4.setVisibility(view.GONE);
                        return false;
                    }
                });

            }
        });

        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer2.setVisibility(View.VISIBLE);
            }
        });

        question3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer3.setVisibility(View.VISIBLE);
            }
        });

        question4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer4.setVisibility(View.VISIBLE);
            }
        });
    }
}