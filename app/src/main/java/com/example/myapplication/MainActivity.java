package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity  extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText ETemail;
    private EditText ETpassword;
    private EditText ETpasswordAccept;

    String email;
    String password;
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        ETemail = (EditText)findViewById(R.id.login);
        ETpassword = (EditText)findViewById(R.id.password);
        ETpasswordAccept = (EditText)findViewById(R.id.passwordAccept);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

        }
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";//регулярное выражение
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);//сначала компилим
        java.util.regex.Matcher m = p.matcher(email);//логин с рег выражением == тру
        return m.matches();
    }

    public void signUpBtn(View view) {//нажатие на кнопку
        if(!ETemail.getText().toString().equals("") && !ETpassword.getText().toString().equals("") && !ETpasswordAccept.getText().toString().equals("")){
            if(ETpassword.getText().toString().length() >= 6){
                if(isValidEmailAddress(ETemail.getText().toString())){
                    if(ETpassword.getText().toString().equals(ETpasswordAccept.getText().toString())){
                        signUp(ETemail.getText().toString(),ETpassword.getText().toString());
                    }
                    else{
                        ETpasswordAccept.setError("Password's does not match");
                    }
                }
                else{
                    ETemail.setError("Email should contain '@' and\n" +
                            "'.' and letters after '.'");
                }
            }
            else {
                ETpassword.setError("Password should contain 6 \n" +
                        "characters");
            }

        }
        else{
            if(ETemail.getText().toString().equals("")){
                ETemail.setError("Fill in the field");
            }
            else if(ETpassword.getText().toString().equals("")){
                ETpassword.setError("Fill in the field");
            }
            else{
                ETpasswordAccept.setError("Fill in the field");
            }
        }
    }

    public void signUp(String email, String password) {// если успешно проверки
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            logInSuccess(user);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }

    private void updateUI(FirebaseUser user) {

    }

    private void logInSuccess(FirebaseUser currentUser){
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("currentUser", currentUser);//ключ, объект
        startActivity(intent);
    }
}
