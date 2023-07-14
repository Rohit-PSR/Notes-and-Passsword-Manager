package com.example.notesandpasswordmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class forgot_password extends AppCompatActivity {

    private EditText mforgotpassword;
    private Button mpasswordrevocerbutton;
    private TextView mgobacktologin;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getSupportActionBar().hide();

        mforgotpassword=findViewById(R.id.forgotpassword);
        mpasswordrevocerbutton=findViewById(R.id.passwordrecoverbutton);
        mgobacktologin=findViewById(R.id.gobacktologin);

        firebaseAuth = FirebaseAuth.getInstance();

        mgobacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(forgot_password.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mpasswordrevocerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=mforgotpassword.getText().toString().trim();
                if(mail.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Your Mail First",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"Mail Sent, You Can Recover The Password",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(forgot_password.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Email Is Wrong Or Account Doesn't Exist",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}