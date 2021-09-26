package com.example.liftpress;
//anodya
import androidx.appcompat.app.AppCompatActivity;
//ohhhh
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button navigate;
    private Button navigate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigate=findViewById(R.id.mainbtn1);
        navigate2=findViewById(R.id.mainbtn2);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
        navigate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });


    }

}
