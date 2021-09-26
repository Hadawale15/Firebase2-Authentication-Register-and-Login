package com.example.authentication_registerandlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    
    EditText email,pass,pass2;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.Email_idR);
        pass=findViewById(R.id.Password_idR1);
        pass2=findViewById(R.id.Password_idR2);
        
    }

   

    public void RegisterMethod2(View view) {
        String EmailR = email.getText().toString();
        String Pass1R = pass.getText().toString();
        String Pass2R = pass2.getText().toString();

        firebaseAuth = FirebaseAuth.getInstance();

        if (EmailR.isEmpty()) {
            email.setError("Email can'be empty");
            email.requestFocus();
            return;
        }
        if (Pass1R.isEmpty()) {
            pass.setError("Password can't be empty");
            pass.requestFocus();
            return;
        }
        if (Pass2R.isEmpty()) {
            pass2.setError("Password can't be empty");
            pass2.requestFocus();
            return;
        }
        if (Pass2R.equals(Pass1R)) {


            firebaseAuth.createUserWithEmailAndPassword(EmailR, Pass1R).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(RegisterActivity.this, "Password Not Matches", Toast.LENGTH_SHORT).show();
        }
    
    
        
    }
}