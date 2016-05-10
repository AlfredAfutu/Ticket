package com.ticket.tiqeet.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.ticket.tiqeet.Activities.MainActivity;
import com.ticket.tiqeet.DatabaseModels.User;
import com.ticket.tiqeet.R;
import com.ticket.tiqeet.tiqeet.Utils.HelperMethods;
import com.ticket.tiqeet.tiqeet.Utils.InternetConnection;
import com.ticket.tiqeet.tiqeet.Utils.TiqeetApi;
import com.ticket.tiqeet.tiqeet.Utils.TiqeetApp;

/**
 * Created by cted on 10/5/15.
 */
public class SignUpFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private EditText firstnameEditText, lastnameEditText, emailEditText, phoneNumberEdidText, passwordEditText, confirmPasswordEditText;
    private Button emailSignUpButton, facebookSignUpButton, googleSignUpButton;
    private TextView feedbackText;
    private InternetConnection internetConnection = new InternetConnection(getActivity());
    private boolean isConnectionEstablished;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.signup_layout, container, false);
        firstnameEditText = (EditText) fragmentView.findViewById(R.id.signUpFirstName);
        lastnameEditText = (EditText) fragmentView.findViewById(R.id.signUpLastName);
        emailEditText = (EditText) fragmentView.findViewById(R.id.signUpEmail);
        phoneNumberEdidText = (EditText) fragmentView.findViewById(R.id.signUpPhoneNumber);
        passwordEditText = (EditText) fragmentView.findViewById(R.id.signUpPassword);
        confirmPasswordEditText = (EditText) fragmentView.findViewById(R.id.confirmPassword);
        emailSignUpButton = (Button) fragmentView.findViewById(R.id.signUpEmailButton);
        facebookSignUpButton = (Button) fragmentView.findViewById(R.id.facebookSignUpButton);
        googleSignUpButton = (Button) fragmentView.findViewById(R.id.googleSignUpButton);
        feedbackText = (TextView) fragmentView.findViewById(R.id.feedback_text);

        emailSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isConnectionEstablished) {
                    getAllDetailsAndSignUp();
                }else{
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("No Internet Connection");
                    dialog.setMessage("Please check your internet connection");
                    dialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));


                        }
                    });
                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    dialog.show();
                }

            }
        });

        facebookSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        googleSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return fragmentView;
    }

    private void getAllDetailsAndSignUp(){
        final String firstName, lastName, email, phoneNumber, password, confirmedPassword;
        firstName = firstnameEditText.getText().toString().trim();
        lastName = lastnameEditText.getText().toString().trim();
        email = emailEditText.getText().toString().trim();
        phoneNumber = phoneNumberEdidText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        confirmedPassword = confirmPasswordEditText.getText().toString().trim();

        if(firstName.equals("") || lastName.equals("") || email.equals("") || password.equals("") || confirmedPassword.equals("")){
            feedbackText.setVisibility(View.VISIBLE);
            feedbackText.setText("Please fields cannot be empty");
        }else if(!password.equals(confirmedPassword)){
            feedbackText.setVisibility(View.VISIBLE);
            feedbackText.setText("Password confirmation is wrong");
        }else if(!HelperMethods.validEmail(email)){
            feedbackText.setVisibility(View.VISIBLE);
            feedbackText.setText("Invalid email address");
        }else{
            feedbackText.setVisibility(View.GONE);
            final ProgressDialog signupProgressDialog = new ProgressDialog(getActivity());
            signupProgressDialog.setMessage("Signing up");
            signupProgressDialog.setIndeterminate(true);
            signupProgressDialog.show();

            Ion.with(TiqeetApp.app)
                    .load(TiqeetApi.getSignUpEndpoint())
                    .setBodyParameter("firstname", firstName)
                    .setBodyParameter("lastname", lastName)
                    .setBodyParameter("email", email)
                    .setBodyParameter("password", password)
                    .setBodyParameter("phone_number", phoneNumber)
                    .setBodyParameter("type", "attendee")
                    .setBodyParameter("role", "admin")
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            if(e!=null){
                               Log.e(TAG, "Sign Up Exception: " + e);
                            }
                            if(result == null){

                            }else{
                                Log.i(TAG, "Server Response: " + result);
                                try {
                                    if( result.get("status").getAsString().equalsIgnoreCase("201") ) {
                                        signupProgressDialog.dismiss();
                                        Log.i(TAG, "After Dialog dismiss");
                                        User new_user = new User();
                                        new_user.userId = result.get("user").getAsJsonObject().get("id").getAsString();
                                        new_user.fName = result.get("user").getAsJsonObject().get("firstname").getAsString();
                                        new_user.lName = result.get("user").getAsJsonObject().get("lastname").getAsString();
                                        new_user.email = result.get("user").getAsJsonObject().get("email").getAsString();
                                        new_user.isThisUser = true;
                                        new_user.isFbkUser = false;
                                        new_user.isGooglePlusUser = false;
                                        new_user.isLoggedOut = false;
                                        if(new_user.save()){
                                            Log.i(TAG, "New User >> " + new_user);
                                        }

                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        getActivity().startActivity(intent);
                                        getActivity().finish();
                                    }
                                }catch(Exception exception){

                                }
                            }

                        }
                    });

        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
