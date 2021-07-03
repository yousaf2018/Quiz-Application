package com.example.quizapplication;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Quiz_Activity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    RecyclerView recyclerView1;
    LinearLayoutManager  linearLayoutManager;
    List<Model_Class>itemList;
    List<quiz>itemList1;
    RecyclerView.Adapter adapter;
    private static final String TAG = "Check";

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.AppBar);
        setSupportActionBar(toolbar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.mainDrawer);
        //Recycler View
        setUpFirsStore();
        initData();
        initRecyclerView();


    }

    private void setUpFirsStore() {
        FirebaseFirestore.getInstance()
                .collection("quizes")
                .whereEqualTo("title","07/02/2021")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG,"On success: we are getting data from firestore");
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot: snapshotList){
                            Log.d(TAG,"On success "+ snapshot.getData().toString());
                            itemList.clear();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.e(TAG,"On Failure"+e);
                    }
                });
    }

    private void initData() {
        itemList =new ArrayList<>();
        itemList.add(new Model_Class(R.drawable.ic_namal_png,"Mobile Application Development"));
        itemList.add(new Model_Class(R.drawable.ic_namal_png,"Computer Networks"));
        itemList.add(new Model_Class(R.drawable.ic_namal_png,"Machine Learning"));
        itemList.add(new Model_Class(R.drawable.ic_namal_png,"Artificial Intelligence"));
        itemList.add(new Model_Class(R.drawable.ic_namal_png,"Numerical Methods"));
        itemList.add(new Model_Class(R.drawable.ic_namal_png,"Information Retrieval"));
        itemList.add(new Model_Class(R.drawable.ic_namal_png,"Web Technologies"));
    }
    public void initRecyclerView() {
        recyclerView1 = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new ProgrammingAdapter(itemList);
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