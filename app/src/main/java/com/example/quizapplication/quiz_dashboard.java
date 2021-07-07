package com.example.quizapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapplication.Adapter.coursesAdapter;
import com.example.quizapplication.Modals.Model_Class;
import com.example.quizapplication.Modals.quiz;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class quiz_dashboard extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    RecyclerView recyclerView1;
    FirebaseAuth firebaseAuth;
    LinearLayoutManager  linearLayoutManager;
    List<Model_Class>itemList;
    ArrayList<quiz> quiz_list = new ArrayList<quiz>();
    RecyclerView.Adapter adapter;
    FirebaseFirestore firestore;
    String quizTitle;
    String userID;
    private static final String TAG = "Check";
    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_);
        Intent intent = getIntent();
        quizTitle = intent.getStringExtra("quizTitle");
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.AppBar);
        setSupportActionBar(toolbar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.mainDrawer);
        //Recycler View
        initData();
        initRecyclerView();
        setUpFirsStore();

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void setUpFirsStore() {
        firestore = FirebaseFirestore.getInstance();
        Map<String, Object> data1 = new HashMap<>();
        Log.d(TAG, "I am here to printing the data of other class activity: "+quizTitle);
        firestore.collection(quizTitle).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot data:list){
                            quiz object = new quiz();
                            Log.d(TAG, "onSuccess: "+data.toString());
                            quiz_list.add(data.toObject(quiz.class));
                            itemList.clear();
                            itemList.add(new Model_Class(R.drawable.ic_icons8_quizlet,quiz_list.get(0).getTitle()));
                            adapter.notifyDataSetChanged();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
    }

    private void initData() {
        itemList =new ArrayList<>();
        itemList.add(new Model_Class(R.drawable.ic_icons8_quizlet,"No Quizes Yet"));
    }
    public void initRecyclerView() {
        recyclerView1 = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new coursesAdapter(itemList);
        recyclerView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}