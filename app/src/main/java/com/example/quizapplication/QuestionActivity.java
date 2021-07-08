package com.example.quizapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapplication.Modals.Model_Class;
import com.example.quizapplication.Modals.Questions;
import com.example.quizapplication.Modals.quiz;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;
    Button button;
    FirebaseFirestore firestore;
    ArrayList<quiz> quiz_list = new ArrayList<quiz>();
    String quiz;
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;
    TextView course_name;
    String userAnswer;
    String answer;
    String course;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.question);
        option1 = findViewById(R.id.opt1);
        option2 = findViewById(R.id.opt2);
        option3 = findViewById(R.id.opt3);
        option4 = findViewById(R.id.opt4);
        button = findViewById(R.id.submit);
        Intent intent = getIntent();
        course = intent.getStringExtra("course_name");
        Log.d("Check2","Finally i found course name->"+course);
        setUpFirsStore();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                button = findViewById(radioId);
                Log.d("Checking","Clicked value-->"+button.getText().toString()+"  Final Answer-->"+answer);
                userAnswer = button.getText().toString();
                if (userAnswer.compareTo(answer)==0){
                    Toast.makeText(getApplicationContext(),"Congratulation right answer",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Sorry wrong answer",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.R)
    private void setUpFirsStore() {
        firestore = FirebaseFirestore.getInstance();
        Map<String, Object> data1 = new HashMap<>();
        firestore.collection(course).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot data:list){
                            quiz object = new quiz();
                            quiz_list.add(data.toObject(quiz.class));
                            Map<String, Questions> obj = new HashMap<>();
                            obj = quiz_list.get(0).getQuestions();
                            Log.d("Check2", "opt1: "+obj.get("question1").getOpt1());
                            Log.d("Check2", "opt2: "+obj.get("question1").getOpt2());
                            Log.d("Check2", "opt3: "+obj.get("question1").getOpt3());
                            Log.d("Check2", "opt4: "+obj.get("question1").getOpt4());
                            Log.d("Check2", "ans: "+obj.get("question1").getAns());
                            Log.d("Check2", "desc: "+obj.get("question1").getDesc());
                            option1.setText(obj.get("question1").getOpt1());
                            option2.setText(obj.get("question1").getOpt2());
                            option3.setText(obj.get("question1").getOpt3());
                            option4.setText(obj.get("question1").getOpt4());
                            textView.setText(obj.get("question1").getDesc());
                            answer = obj.get("question1").getAns();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Check", "onFailure: ");
                    }
                });
    }

    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        button = findViewById(radioId);
        Toast.makeText(this,"Selected option is: "+button.getText(),Toast.LENGTH_SHORT).show();
    }
}