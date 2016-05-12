package com.rahadi.sipadu.activities;

import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.rahadi.sipadu.R;
import com.rahadi.sipadu.adapters.JadwalPager;
import com.rahadi.sipadu.fragments.DatePickerFragment;

public class JadwalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_jadwal);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title_activity_jadwal);
        }

        JadwalPager adapter = new JadwalPager(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager_jadwal);
        pager.setAdapter(adapter);
        pager.setCurrentItem(4999);

        PagerTabStrip strip = (PagerTabStrip) findViewById(R.id.strip_jadwal);
        strip.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
        strip.setTabIndicatorColor(getResources().getColor(android.R.color.white));
        strip.setTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_jadwal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }else if (id == R.id.calendar) {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
            Toast.makeText(JadwalActivity.this, "Mantap", Toast.LENGTH_LONG).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}