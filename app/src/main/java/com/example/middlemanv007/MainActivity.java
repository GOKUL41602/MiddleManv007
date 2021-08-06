package com.example.middlemanv007;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    TextInputLayout userName, password;
    Button loginButton, forgetPasswordButton;
    String userNameText, passwordText;
    RelativeLayout relativeLayout;
    TextView newUserButton;
    DatabaseReference reference;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, UserRegistration.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeStrings();
                if (validateUser()) {
                    if (validatePassword()) {
                        progressBar.setVisibility(View.VISIBLE);
                        validateUserName();
                    } else {
                        validatePassword();
                    }
                } else {
                    validateUser();
                }

            }
        });

        forgetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeStrings();

                if (validateUser()) {
                    progressBar.setVisibility(View.VISIBLE);
                    validateUserNameForForgetPassword();
                } else {
                    validateUser();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
    }

    private void validateUserNameForForgetPassword() {
        reference = FirebaseDatabase.getInstance().getReference("UsersDto");
        Query query = reference.orderByChild("userName").startAt(userNameText).endAt(userNameText + "\uf8ff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String userNameFromDB = snapshot.child(userNameText).child("userName").getValue(String.class);
                    if (userNameFromDB.equals(userNameText)) {
                        userName.setError(null);
                        userName.setErrorEnabled(true);
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
                        intent.putExtra("userName", userNameText);
                        intent.putExtra("userMode", "Normal");
                        startActivity(intent);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        userName.setError("User name doesn't exists");
                        userName.setErrorEnabled(true);
                        userName.requestFocus();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void validateUserName() {
        reference = FirebaseDatabase.getInstance().getReference("UsersDto");

        Query query = reference.orderByChild("userName").startAt(userNameText).endAt(userNameText + "\uf8ff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String userNameFromDB = snapshot.child(userNameText).child("userName").getValue(String.class);
                    if (userNameText.equals(userNameFromDB)) {
                        userName.setError(null);
                        userName.setErrorEnabled(true);
                        String passwordFromDB = snapshot.child(userNameText).child("password").getValue(String.class);
                        if (passwordText.equals(passwordFromDB)) {
                            password.setErrorEnabled(false);
                            password.setError(null);
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(MainActivity.this, SalesMela.class);
                            intent.putExtra("userName", userNameText);
                            startActivity(intent);
                            MainActivity.this.finish();
                        } else {
                            password.setErrorEnabled(true);
                            progressBar.setVisibility(View.GONE);
                            password.setError("Incorrect Password");
                            password.requestFocus();
                        }
                    } else {
                        progressBar.setVisibility(View.GONE);
                        userName.setError("User name doesn't exists");
                        userName.setErrorEnabled(true);
                        userName.requestFocus();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    userName.setError("User name doesn't exists");
                    userName.setErrorEnabled(true);
                    userName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private boolean validateUser() {
        if (userNameText.equals("")) {
            userName.setErrorEnabled(true);
            userName.setError("Enter UserName");
            return false;
        } else {
            userName.setErrorEnabled(false);
            userName.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        if (passwordText.equals("") || passwordText.length() < 6) {
            password.setError("Enter Valid 6 digit  Password");
            password.setErrorEnabled(true);
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private void initializeStrings() {
        userNameText = userName.getEditText().getText().toString();
        passwordText = password.getEditText().getText().toString();
    }

    private void initializeViews() {
        userName = findViewById(R.id.loginPage_userName);
        password = findViewById(R.id.loginPage_password);
        loginButton = findViewById(R.id.loginPage_loginButton);
        relativeLayout = findViewById(R.id.mainActivity_relLayout);
        newUserButton = findViewById(R.id.loginPage_newUserButton);
        forgetPasswordButton = findViewById(R.id.loginPage_forgetPasswordButton);
        progressBar = findViewById(R.id.loginPage_progressBar);
    }
}