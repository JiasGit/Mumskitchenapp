package dream.lab.mumskitchenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import dream.lab.mumskitchenapp.Models.User;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText signupName, signupEmail, signupPassword, confirmPassword;
    private TextView clickMember, signUp;
    private ProgressBar progressbarSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        signupName = findViewById(R.id.editTextSuName);
        signupEmail = findViewById(R.id.editTextSuEmail);
        signupPassword = findViewById(R.id.editTextSuPassword);
        confirmPassword = findViewById(R.id.editTextSuConfirmPassword);

        clickMember = findViewById(R.id.textMember);
        signUp = (Button)findViewById(R.id.btnSignUp);
        progressbarSignup = findViewById(R.id.progressBar2);

        clickMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = signupName.getText().toString().trim();
                String email = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();
                String confirmPW = confirmPassword.getText().toString().trim();

                if(name.isEmpty()){
                    signupName.setError("Name is required!");
                    signupName.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    signupEmail.setError("Email is required!");
                    signupEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    signupEmail.setError("Please enter a valid email!");
                    signupEmail.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    signupPassword.setError("Password is required!");
                    signupPassword.requestFocus();
                    return;
                }
                if(confirmPW.isEmpty()){
                    confirmPassword.setError("Please re-enter your password!");
                    confirmPassword.requestFocus();
                    return;
                }
                if(!confirmPW.equals(password)){
                    confirmPassword.setError("Different from password");
                    confirmPassword.requestFocus();
                    return;
                }

                progressbarSignup.setVisibility(view.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    // write user information to firebase real time database
                                    User user = new User(name, email);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(SignupActivity.this, "User has been registered successfully! Please log in!", Toast.LENGTH_LONG).show();
                                                        progressbarSignup.setVisibility(View.GONE);
                                                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                                    }else{
                                                        Toast.makeText(SignupActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                                        progressbarSignup.setVisibility(View.GONE);
                                                    }

                                                }
                                            });

                                }else{
                                    Toast.makeText(SignupActivity.this, "Error!"+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    progressbarSignup.setVisibility(View.GONE);
                                }

                            }
                        });

            }

        });

    }
}