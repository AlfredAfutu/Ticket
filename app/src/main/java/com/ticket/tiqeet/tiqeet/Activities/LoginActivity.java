package com.ticket.tiqeet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.ticket.tiqeet.DatabaseModels.User;
import com.ticket.tiqeet.Fragments.LoginSignUpFragment;

/**
 * Created by cted on 7/6/15.
 */
public class LoginActivity extends FragmentActivity{

    //private EditText usernameEditText, passwordEditText;
    FragmentManager fragmentManager;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //User user = User.getThisUser();
        if(User.getUsers().size() == 0 || User.getUsers() == null) {
            //setContentView(R.layout.login_layout);
            //initializeViews();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(android.R.id.content, new LoginSignUpFragment()).commit();
            //loginButton.setOnClickListener(this);
            //signupButton.setOnClickListener(this);
        }else{
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
