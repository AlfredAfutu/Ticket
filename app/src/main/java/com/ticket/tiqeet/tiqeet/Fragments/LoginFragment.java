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
import com.ticket.tiqeet.tiqeet.Utils.InternetConnection;
import com.ticket.tiqeet.tiqeet.Utils.TiqeetApi;
import com.ticket.tiqeet.tiqeet.Utils.TiqeetApp;

/**
 * Created by cted on 10/5/15.
 */
public class LoginFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private EditText emailEditText, passwordEditText;
    private Button emailLoginButton, facebookLoginButton, googleLoginButton;
    private TextView feedbackText;
    private Intent intent;
    private InternetConnection internetConnection = new InternetConnection(getActivity());
    private boolean isConnectionEstablished;
   // private Session currentSession;


    public LoginFragment(){
        ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.login_layout, container, false);
        emailEditText = (EditText) fragmentView.findViewById(R.id.loginEmail);
        passwordEditText = (EditText) fragmentView.findViewById(R.id.loginPassword);
        emailLoginButton = (Button) fragmentView.findViewById(R.id.loginEmailButton);
        facebookLoginButton = (Button) fragmentView.findViewById(R.id.facebookLoginButton);
        googleLoginButton = (Button) fragmentView.findViewById(R.id.googleLoginButton);
        feedbackText = (TextView) fragmentView.findViewById(R.id.feedback_text_login);

        emailLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnectionEstablished) {
                    loginEmail();
                }else{
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("No Internet Connection");
                    dialog.setMessage("Please check your internet connection and try again");
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

        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnectionEstablished) {

                }else{
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("No Internet Connection");
                    dialog.setMessage("Please check your internet connection and try again");
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

        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return fragmentView;
    }

    public void loginEmail(){
        final String email, password;
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        if(email.equals("") || password.equals("")){
            feedbackText.setVisibility(View.VISIBLE);
            feedbackText.setText("Please fields cannot be empty");
        }else{
            feedbackText.setVisibility(View.GONE);
            final ProgressDialog loginProgressDialog = new ProgressDialog(getActivity());
            loginProgressDialog.setMessage("Logging in");
            loginProgressDialog.setIndeterminate(true);
            loginProgressDialog.show();

            Ion.with(TiqeetApp.app)
                    .load(TiqeetApi.getLoginEndpoint())
                    .setBodyParameter("email", email)
                    .setBodyParameter("password", password)
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
                                        loginProgressDialog.dismiss();
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

   /* public void facebookLoginInit() {
        Log.e(TAG, "fbk init hit ");
        final ProgressDialog facebookLoginProgressDialog = new ProgressDialog(getActivity());
        facebookLoginProgressDialog.setMessage("Contacting facebook");
        facebookLoginProgressDialog.setIndeterminate(true);
        facebookLoginProgressDialog.show();

        if (currentSession == null || currentSession.getState().isClosed() ) {
            Session session = new Session.Builder( getActivity() ).build();
            Session.setActiveSession(session);
            currentSession = session;
        }

        if (currentSession.isOpened()) {
            // Do whatever u want. User has logged in
            Log.e(TAG, "handle current ssession openned");
            Log.e(TAG, " just b4 handle userInfo");
            facebookLoginProgressDialog.dismiss();
            handleUserInfo();
        } else if (!currentSession.isOpened() ) {

            Log.e(TAG,"not opned " );

            // Ask for username and password
            Session.OpenRequest op = new Session.OpenRequest((Activity) getActivity());

            //op.setLoginBehavior(SessionLoginBehavior.SUPPRESS_SSO);
            op.setCallback(null);

            List<String> permissions = new ArrayList<String>();
//            permissions.add("publish_stream");
            permissions.add("user_likes");
            permissions.add("email");
            permissions.add("user_birthday");
            op.setPermissions(permissions);

            facebookLoginProgressDialog.dismiss();

            Session session = new Session.Builder(getActivity()).build();
            Session.setActiveSession(session);
            session.addCallback(statusCallback);
            session.openForRead(op);

        }



    }

    private void handleUserInfo( ) {
        Log.e(TAG, "handle UserInfo called" + currentSession.getState() );
        if (currentSession.isOpened()) {
            Log.e(TAG, "session openned");
            Session.openActiveSession(this, true, new Session.StatusCallback() {

                @Override
                public void call(final Session session, SessionState state,
                                 Exception exception) {
                    Log.e(TAG, "call called");
                    if (session.isOpened()) {
                        Log.e(TAG, "session openend ");
                        TiqeetApp.USER_FBK_ACCOUNT_TOKEN = session.getAccessToken();

                        //public Request(Session session, String graphPath, Bundle parameters, HttpMethod httpMethod)

                        //Request newReq = new Request(session,USER_PROFILE_URL,parameters, null );
                        Request.newMeRequest(session,
                                handleUserInfoCallback
                        ).executeAsync();

                    }
                }
            });
        }else{

        }
    }

    private Session.StatusCallback statusCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            Log.e(TAG, "status call back called");
            currentSession = session ;
            handleUserInfo();
        }
    };

    private Request.GraphUserCallback handleUserInfoCallback = new Request.GraphUserCallback() {
        @Override
        public void onCompleted(GraphUser user, final Response response) {
            if(user != null ){
                Log.e(TAG, "user >>" + user );
                Log.e(TAG, " before sending data to server" + user.getUsername());

                loginSocialAuth(user.getId(), "facebook", user.getFirstName(), user.getLastName, (String)user.asMap().get("email"));
                Log.e(TAG, " after sending data to server" );

            };
            if(response != null){
                Log.e(TAG, "user >>" + response );
                Log.e(TAG, "user >>" + response.toString() );

            };
        }
    };*/

    public void loginSocialAuth(String socialMediaId, final String socialMediaType, String firstname, String lastname, String email){
        final ProgressDialog loginProgressDialog = new ProgressDialog(getActivity());
        loginProgressDialog.setMessage("Logging in");
        loginProgressDialog.setIndeterminate(true);
        loginProgressDialog.show();

        Ion.with(TiqeetApp.app)
                .load(TiqeetApi.getLoginEndpoint())
                .setBodyParameter("authType", "social")
                .setBodyParameter("socialMediaId", socialMediaId)
                .setBodyParameter("socialMediaType", socialMediaType)
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
                                    loginProgressDialog.dismiss();
                                    User new_user = new User();
                                    new_user.userId = result.get("user").getAsJsonObject().get("id").getAsString();
                                    new_user.fName = result.get("user").getAsJsonObject().get("firstname").getAsString();
                                    new_user.lName = result.get("user").getAsJsonObject().get("lastname").getAsString();
                                    new_user.email = result.get("user").getAsJsonObject().get("email").getAsString();
                                    new_user.isThisUser = true;
                                    new_user.isLoggedOut = false;
                                    if(socialMediaType.equalsIgnoreCase("facebook")){
                                        new_user.isFbkUser = true;
                                        new_user.isGooglePlusUser = false;
                                    }else if(socialMediaType.equalsIgnoreCase("gplus")){
                                        new_user.isFbkUser = false;
                                        new_user.isGooglePlusUser = true;
                                    }
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
       /* if (Session.getActiveSession() != null)
            Session.getActiveSession().onActivityResult(this, requestCode,
                    resultCode, data);

        currentSession = Session.getActiveSession();
        if (currentSession == null || currentSession.getState().isClosed()) {
            Session session = new Session.Builder(getActivity()).build();
            Session.setActiveSession(session);
            currentSession = session;
        }

        handleUserInfo();*/
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
        isConnectionEstablished = internetConnection.isConnectingToInternet();

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
