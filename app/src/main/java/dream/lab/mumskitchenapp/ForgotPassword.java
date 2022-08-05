package dream.lab.mumskitchenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private TextView etEnterEmail;
    private Button resetPassword;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEnterEmail = findViewById(R.id.editTextEnterEmail);
        resetPassword = findViewById(R.id.buttonResetPassword);
        progressBar = findViewById(R.id.progressBar3);

        auth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

    }

    private void resetPassword() {
        String email = etEnterEmail.getText().toString().trim();

        if(email.isEmpty()){
            etEnterEmail.setError("Email is required!");
            etEnterEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEnterEmail.setError("Invalid Email Address!");
            etEnterEmail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Reset password link has been sent to your email!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.VISIBLE);
                    startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                }else{
                    Toast.makeText(ForgotPassword.this, "Oops! Something's wrong, please try again!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}