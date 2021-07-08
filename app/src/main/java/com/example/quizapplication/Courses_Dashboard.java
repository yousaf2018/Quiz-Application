package com.example.quizapplication;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

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
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Courses_Dashboard extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    RecyclerView recyclerView1;
    TextView course_name;
    LinearLayoutManager  linearLayoutManager;
    List<Model_Class>itemList;
    ArrayList<quiz> quiz_list = new ArrayList<quiz>();
    RecyclerView.Adapter adapter;
    private static final String TAG = "Check";
    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @RequiresApi(api = Build.VERSION_CODES.R)
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
        initData();
        initRecyclerView();


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
        itemList.add(new Model_Class(R.drawable.ic_namal_png,"Operating systems"));
        itemList.add(new Model_Class(R.drawable.ic_namal_png,"Compiler Construction"));
        itemList.add(new Model_Class(R.drawable.ic_namal_png,"Databases"));
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