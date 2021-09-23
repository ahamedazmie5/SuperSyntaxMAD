package com.example.liftpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    private TextView userNameEdt , pwdEdt;
    private Button registerbtn;
    private FirebaseAuth mAuth;
    private TextView LoginTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_signup);
        userNameEdt = findViewById(R.id.signupemail1);
        pwdEdt = findViewById(R.id.signuppassword1);
        registerbtn = findViewById(R.id.sigupbtn1);
        mAuth = FirebaseAuth.getInstance();

        registerbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String userName = userNameEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                if( (userName.isEmpty())||(!Patterns.EMAIL_ADDRESS.matcher(userName).matches())) {
                    Toast.makeText(signup.this, "Please Insert Valid Email ", Toast.LENGTH_SHORT).show();
                    return;
                } else if (pwd.isEmpty()) {
                    Toast.makeText(signup.this, "Please insert data to Password ", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mAuth.createUserWithEmailAndPassword(userName, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(signup.this, "User Registered successfull!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(signup.this, login.class);
                                startActivity(i);
                                finish();

                            } else {
                                Toast.makeText(signup.this, "User Registration Failed!!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }}