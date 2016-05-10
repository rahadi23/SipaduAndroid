package com.rahadi.sipadu.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rahadi.sipadu.R;
import com.rahadi.sipadu.fragments.NimFragment;
import com.rahadi.sipadu.fragments.PasswordFragment;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences pernah_login;
    Boolean loggedin, isiNim;
    Button next_1;
    EditText nim_input, pass_input;
    String namauser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        CEK PERNAH LOGIN
        pernah_login = LoginActivity.this.getSharedPreferences("LOGIN_FLAG", Context.MODE_PRIVATE);
        loggedin = pernah_login.getBoolean("LOGIN_FLAG", false);
        if (loggedin) {
            Intent i = new Intent(getApplicationContext(), SplashScreenActivity.class);
            startActivity(i);
            finish();
        }

        pernah_login = LoginActivity.this.getSharedPreferences("INPUT_NIM", Context.MODE_PRIVATE);
        isiNim = pernah_login.getBoolean("INPUT_NIM", false);
        if (isiNim) {
            transaction(new PasswordFragment());
        } else {
            transaction(new NimFragment());
        }

//        TOMBOL NEXT
        next_1 = (Button)findViewById(R.id.next_1);
        next_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pernah_login = LoginActivity.this.getSharedPreferences("INPUT_NIM", Context.MODE_PRIVATE);
                isiNim = pernah_login.getBoolean("INPUT_NIM", false);
                if(isiNim) {
                    pass_input = (EditText)findViewById(R.id.password_input);
                    String pass = pass_input.getText().toString();
                    String pass_match = "admin123";
                    if(pass.equals(pass_match)) {
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);

                        pernah_login = LoginActivity.this.getSharedPreferences("LOGIN_FLAG", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pernah_login.edit();
                        editor.putBoolean("LOGIN_FLAG", true).apply();
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "PASSWORD SALAH", Toast.LENGTH_LONG).show();
                    }
                } else {
                    nim_input = (EditText)findViewById(R.id.nim_input);
                    String nim = nim_input.getText().toString();
                    String nim_match = "14.8325";
                    if (nim.equals(nim_match)) {
                        Toast.makeText(getApplicationContext(), "MATCHED", Toast.LENGTH_LONG).show();
                        SharedPreferences pernahNim = getSharedPreferences("INPUT_NIM", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pernahNim.edit();
                        editor.putBoolean("INPUT_NIM", true).apply();
//
//                        PasswordFragment passwordFragment = new PasswordFragment();
//
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                            Slide slide = new Slide(Gravity.RIGHT);
//                            slide.setDuration(1000);
//                            passwordFragment.setEnterTransition(slide);
//                        }
                        transaction(new PasswordFragment());

                    } else {
                        Toast.makeText(getApplicationContext(), "MISMATCHED", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

//        Window window = this.getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
//        }

    }

    public void transaction(Fragment mFragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction mtransaction = fragmentManager.beginTransaction();
        mtransaction.replace(R.id.kontainerfragment, mFragment);
        mtransaction.commit();
    }

}
