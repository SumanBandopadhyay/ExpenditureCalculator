package com.expenditurecalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Spinner spinner;
    private Button btnSubmit;
    private DbHelper dbHelper;
    private EditText etAmount;
    private ArrayList<String> resultArrayList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnSubmit = (Button) findViewById(R.id.btn_submit);
        etAmount = (EditText) findViewById(R.id.et_amount);
        resultArrayList = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.lst_output);

        spinner = (Spinner) findViewById(R.id.spinner_jobs);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.expense_areas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        dbHelper = new DbHelper(this);

        showList();

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        String type = spinner.getSelectedItem().toString();
        String amount = etAmount.getText().toString();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        if (dbHelper.insertIntoTable(date, type, amount)) {
            Toast.makeText(getApplicationContext(), "Data Inserted..!!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Failed to Insert..!!", Toast.LENGTH_LONG).show();
        }
        showList();
        storetype(type);
    }

    private void storetype(String type) {
    }

    private void showList() {
        //resultArrayList = dbHelper.getDateElement(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        resultArrayList = dbHelper.getData();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, resultArrayList);
        listView.setAdapter(adapter);
    }
}
