package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firestore.v1.TargetOrBuilder;

public class Result extends AppCompatActivity {
    Button button;
    ImageView imageView;
    String Result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        imageView = findViewById(R.id.ReusltImage);
        button = findViewById(R.id.button);
        Intent intent = getIntent();
        Result = intent.getStringExtra("Result");
        Toast.makeText(getApplicationContext(),""+Result,Toast.LENGTH_LONG).show();
        boolean resultOfComparison=Result.equals("1");
        if(resultOfComparison==true){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_congrats));
        }
        else{
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_sorry));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Courses_Dashboard.class)); }
        });


    }
}