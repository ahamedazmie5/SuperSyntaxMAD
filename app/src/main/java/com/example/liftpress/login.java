package com.example.liftpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class  login extends AppCompatActivity {

    private EditText userNameEdt , pwdEdt;
    private Button Loginbtn;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_login);
        userNameEdt = findViewById(R.id.inputemail1);
        pwdEdt = findViewById(R.id.inputpassword1);
        Loginbtn = findViewById(R.id.loginbtn1);

        mAuth = FirebaseAuth.getInstance();


        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd)) {
                    Toast.makeText(login.this, "Please enter Details!", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    mAuth.signInWithEmailAndPassword(userName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {

                                Toast.makeText(login.this, "Login Successfull!!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(login.this,User.class);
                                startActivity(i);
                                finish();

                            }else{

                                Toast.makeText(login.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

}
