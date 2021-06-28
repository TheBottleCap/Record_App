package com.example.record;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth auth,fAuth;

    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Button signInBtn = findViewById(R.id.signInBtn);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity3.class));
            finish();
        }

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailId = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (emailId.isEmpty()){
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }
                if (pass.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }
                fAuth.createUserWithEmailAndPassword(emailId,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "User added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity3.class));
                        }else{
                            Toast.makeText(MainActivity.this, "user not added", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


        findViewById(R.id.loginTxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity3.class));
            }
        });





        //google sign in belo
//        auth = FirebaseAuth.getInstance();
//        createRequest();
//        findViewById(R.id.sign_in_button).setOnClickListener(v -> resultLauncher.launch(new Intent(mGoogleSignInClient.getSignInIntent())));

    }
//--------------------------google sign in---------------------
//    private void createRequest() {
//        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions
//                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//    }
//
//    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//
//        if (result.getResultCode() == Activity.RESULT_OK) {
//            Intent intent = result.getData();
//
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//
//                assert account != null;
//                firebaseAuthWithGoogle(account.getIdToken());
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//
//            }
//        }
//
//    });
//
//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        auth.signInWithCredential(credential)
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        // Sign in success, update UI with the signed-in user's information
//                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
//                        finish();
//                        Toast.makeText(MainActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    //    When user is logged in once and didn't logged out it will directly go to main activity
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (auth.getCurrentUser() != null) {
//
//            startActivity(new Intent(getApplicationContext(), MainActivity2.class));
//            finish();
//
//        }
//    }
    //--------------------------------------------------------

}