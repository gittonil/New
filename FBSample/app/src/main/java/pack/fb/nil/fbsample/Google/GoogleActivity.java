package pack.fb.nil.fbsample.Google;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import pack.fb.nil.fbsample.R;

public class GoogleActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //.addApi(Plus.API,Plus.PlusOptions.builder().build())
                .build();
        googleApiClient.hasConnectedApi(Plus.API);

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(googleSignInOptions.getScopeArray());


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });


    }

    private void signIn() {
        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 1) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //      handleSignInResult(result);
        }
    }
//
//    @Override
//    protected void onStart(){
//        super.onStart();
//        googleApiClient.connect();
//    }
//
//    @Override
//    protected void onStop(){
//        super.onStop();
//        if(googleApiClient.isConnected()){
//            googleApiClient.disconnect();
//        }
//    }
//
//    @Override
//    protected void onResume(){
//        super.onResume();
//        if(googleApiClient.isConnected()){
//            googleApiClient.connect();
//        }
//    }

    @Override
    public void onConnected(Bundle bundle) {

        getProfileInfo();
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    private void getProfileInfo() {

        try{

            if(Plus.PeopleApi.getCurrentPerson(googleApiClient)!=null){
                Person currentPerson = Plus.PeopleApi.getCurrentPerson(googleApiClient);
                setPersonalInfo(currentPerson);
            }else {
                Toast.makeText(this,"No personal info display", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void setPersonalInfo(Person currentperson) {

        String personName = currentperson.getDisplayName();
        String emailid = Plus.AccountApi.getAccountName(googleApiClient);

        TextView show = (TextView) findViewById(R.id.textView2);

        show.setText("Successfully login as "+personName);
        show.setText("mail at "+emailid);

    }
}
