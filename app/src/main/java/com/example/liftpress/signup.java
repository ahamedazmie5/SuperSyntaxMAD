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

public class signup extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, register;
    private EditText Email, Password;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.signuptxt1);
        banner.setOnClickListener(this);

        register = (Button) findViewById(R.id.sigupbtn1);
        register.setOnClickListener(this);

        Email=(EditText) findViewById(R.id.signupemail1);
        Password=(EditText) findViewById(R.id.signuppassword1);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signuptxt1:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.sigupbtn1:
                register();
                break;
        }

    }

    private void register() {
        String email= Email.getText().toString().trim();
        String password= Password.getText().toString().trim();

        if(email.isEmpty()){
            Email.setError("Email is Required!");
            Email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Please Provide a Valid Email!");
            Email.requestFocus();
            return;

        }

        if(password.isEmpty()){
            Password.setError("Password is Required!");
            Password.requestFocus();
            return;

        }

        if(password.length() < 6){
            Password.setError("Min Password Length Should be 6 Characters!");
            Password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(signup.this, "User has been registered Successfully!", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(signup.this,"Failed to Register! Try Again!", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                        }else{
                            Toast.makeText(signup.this,"Failed to Register! Try Again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}