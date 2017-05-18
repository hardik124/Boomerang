package com.boomerang.boomerang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.boomerang.boomerang.base.BaseActivity;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


public class login extends BaseActivity {
    private static final int GOOGLE_SIGN_IN = 0;
    private SignInButton mGoogleSignIn;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //TODO : Add this in Application class

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);

        mGoogleSignIn = (SignInButton) findViewById(R.id.google_sign_in_btn);
        mGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();

            }
        });
    }

    private void signInWithGoogle() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        final Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        GoogleSignInResult result;
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN) {
            result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                final GoogleApiClient client = mGoogleApiClient;
                showSnack("Signed in");
                GoogleSignInAccount account = result.getSignInAccount();
                showToast(account.getDisplayName());
                //handleSignInResult(...)
            } else {
                //handleSignInResult(...);
                showToast("Signed in");
            }
        } else {
            // Handle other values for requestCode
            showToast("ERROR");
        }

    }

}
