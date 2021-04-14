package com.example.myapplication;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import com.vk.api.sdk.VK;
//import com.vk.api.sdk.auth.VKAccessToken;
//import com.vk.api.sdk.auth.VKAuthCallback;
//import com.vk.api.sdk.auth.VKScope;

public class MainActivity3 extends AppCompatActivity {


    //private static final int VK_REQUEST = 282;//вк спец чтобы авториз
    private FirebaseAuth mAuth;
    private EditText ETemail;
    private EditText ETpassword;
    private SignInButton signInButtonGoogle;//для гугла
    String email;
    String password;
    String TAG;
    private GoogleSignInClient mGoogleSignInClient;//об класса клиента гугла

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();//кладем в интент инфу и др класс может ее получ.передал об собственного класса
        setContentView(R.layout.activity_main3);//
        mAuth = FirebaseAuth.getInstance();
        ETemail = (EditText) findViewById(R.id.login);
        ETpassword = (EditText) findViewById(R.id.password);

        if (arguments != null) {//есть ли аргументы
            FirebaseUser currentUserInt = (FirebaseUser) arguments.get("currentUser");//получаю текущего юзера. " ключ "
            ETemail.setText(currentUserInt.getEmail());//считывает логин.
        }
        googleIntegration();//0инфа для гугла
    }

    private void googleIntegration() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        findViewById(R.id.sign_in_google_button).setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_google_button://айди кнопки
                singInGoogleButton();//вызывается при наж на кнопку
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }


    public void singInGoogleButton() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();//создали намерение
        startActivityForResult(signInIntent, 1);//вызывает онактивитти
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {//для работы гугл и вк
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1:
                if(resultCode == RESULT_OK) {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    handleSignInResult(task);
                }
                else{

                }
                break;
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            Intent intent = new Intent(this,  MainActivity2.class);//переход на кальк
            startActivity(intent);
            Log.w(TAG, "login complite");
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
        } catch (ApiException e) {

            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(MainActivity3.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
            updateUI(null);
        }
    }


    public void logInBtn(View view) {
        if (!ETemail.getText().toString().equals("") && !ETpassword.getText().toString().equals("")) {
            logIn(ETemail.getText().toString(), ETpassword.getText().toString());
        } else {
            if (ETemail.getText().toString().equals("")) {
                ETemail.setError("Fill in the field");
            } else {
                ETpassword.setError("Fill in the field");
            }
        }
    }


    private void logIn(String email, String password) {//для бд
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(MainActivity3.this, "Authentication successfully.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            logInSuccess(user);
                            updateUI(user);
                        } else {

                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity3.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void signUpBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void logInSuccess(FirebaseUser user) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("currentUser", user);
        startActivity(intent);
    }

    private void updateUI(FirebaseUser user) {
    }


}
