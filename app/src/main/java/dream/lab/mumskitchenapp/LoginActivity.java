package dream.lab.mumskitchenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextView etEmail, etPassword, btnClickSignUp, forgotPassword;
    private Button btnLogin;
    private ProgressBar progessbar;

    //Testing
    private ImageView imageLogoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        forgotPassword = findViewById(R.id.textForgotPw);
        btnLogin = findViewById(R.id.buttonLogin);
        btnClickSignUp = findViewById(R.id.textSignUpClick);
        progessbar = findViewById(R.id.progressBar);

        imageLogoView = findViewById(R.id.imageLogo);

        btnClickSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
        });

        imageLogoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmail.setText("tracy@gmail.com");
                etPassword.setText("123456");
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                String Email = etEmail.getText().toString().trim();
                String Password = etPassword.getText().toString().trim();

                if(Email.isEmpty()){
                    etEmail.setError("Email is required!");
                    etEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    etEmail.setError("Please enter a valid email!");
                    etEmail.requestFocus();
                    return;
                }
                if(Password.isEmpty()){
                    etPassword.setError("Password is required!");
                    etPassword.requestFocus();
                    return;
                }

                progessbar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //redirect to main page
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            progessbar.setVisibility(View.GONE);
                        }else{
                            Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                            progessbar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });


    }
}