package pack.fb.nil.fbsample.Facebook;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import pack.fb.nil.fbsample.Google.GoogleActivity;
import pack.fb.nil.fbsample.R;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private AccessToken accessToken;
    private ProfileTracker profileTracker;

  //  private AsyncFacebookRunner asyncFacebookRunner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        accessToken = AccessToken.getCurrentAccessToken();

        Button button = (Button) findViewById(R.id.button2);
        final TextView textView = (TextView) findViewById(R.id.textView);
        LoginButton loginButton = (LoginButton) findViewById(R.id.button);

        assert loginButton != null;
        loginButton.setReadPermissions("user_friends","email","user_birthday");




        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

//                GraphRequest request = new GraphRequest.newMeRequest(
//                        loginResult.getAccessToken(),
//                        new GraphRequest.GraphJSONObjectCallback(){
//                            @Override
//                            public void onCompleted(JSONObject object, GraphResponse response){
//                                Log.v("LogInActivity", response.toString());
//
//                                try {
//                                    String email = object.getString("email");
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//
//                Bundle parameters = new Bundle();
//                parameters.putString("fields","id,name,email");
//                request.setParameters(parameters);
//                request.executeAsync();

                Profile profile = Profile.getCurrentProfile();

                 try {
                     textView.setText(
                             "Successfully login as "
                             + profile.getFirstName()+" "
                             + profile.getLastName()
//                             +"\nId :"
//                             + profile.getId()
                     );

                 }catch (Exception e){
                     textView.setText("Null Pointer error for displaying the name of user");
                 }

            }

            @Override
            public void onCancel() {

                textView.setText("Log in Cancel");
            }

            @Override
            public void onError(FacebookException error) {

                textView.setText("Log in attempt failed");
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoogleActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



}
