package com.example.middlemanv007;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ForgetPassword extends AppCompatActivity {

    TextInputLayout createPassword, confirmPassword;
    String userName, userMode, userType, createPasswordText, confirmPasswordText;
    Button changePasswordButton;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        try {
            userName = getIntent().getStringExtra("userName");
            userType = getIntent().getStringExtra("userType");
            userMode = getIntent().getStringExtra("userMode");
        } catch (Exception e) {
            userType = null;
            userName = null;
            userMode = null;
        }
        initializeViews();

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeStrings();
                if (validateCreatePassword()) {
                    if (validateConfirmPassword()) {
                        if (verifyPassword()) {
                            if (userMode.equals("Normal")) {
                                reference = FirebaseDatabase.getInstance().getReference("UsersDto");
                                Query query = reference.orderByChild("userName").startAt(userName).endAt(userName + "\uf8ff");
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            reference.child(userName).child("password").setValue(confirmPasswordText);
                                            Toast.makeText(ForgetPassword.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ForgetPassword.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });
                            } else if (userMode.equals("Authorized")) {
                                reference = FirebaseDatabase.getInstance().getReference("AuthorizedUsersDto");
                                Query query = reference.orderByChild("userName").startAt(userName).endAt(userName + "\uf8ff");
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            reference.child(userName).child("password").setValue(confirmPasswordText);
                                            Toast.makeText(ForgetPassword.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ForgetPassword.this, AuthorizedUserLogin.class);
                                            intent.putExtra("userName", userName);
                                            intent.putExtra("userType", userType);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });
                            }
                        } else {
                            verifyPassword();
                        }
                    } else {
                        validateConfirmPassword();
                    }
                } else {
                    validateCreatePassword();
                }
            }
        });

    }

    private void initializeStrings() {
        createPasswordText = createPassword.getEditText().getText().toString();
        confirmPasswordText = confirmPassword.getEditText().getText().toString();
    }

    private boolean validateCreatePassword() {
        if (createPasswordText.equals("") || createPasswordText.length() < 6) {
            createPassword.setErrorEnabled(true);
            createPassword.setError("Create 6 digit Password");
            return false;
        } else {
            createPassword.setErrorEnabled(false);
            createPassword.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        if (confirmPasswordText.equals("") || confirmPasswordText.length() < 6) {
            confirmPassword.setErrorEnabled(true);
            confirmPassword.setError("Enter Confirm 6 digit Password");
            return false;
        } else {
            confirmPassword.setErrorEnabled(false);
            confirmPassword.setError(null);
            return true;
        }
    }

    private boolean verifyPassword() {
        if (!confirmPasswordText.equals(createPasswordText)) {
            confirmPassword.setErrorEnabled(true);
            confirmPassword.setError("Password doesn't match");
            return false;
        } else {
            confirmPassword.setErrorEnabled(false);
            confirmPassword.setError(null);
            return true;
        }
    }

    private void initializeViews() {
        createPassword = findViewById(R.id.forgetPassword_createPassword);
        confirmPassword = findViewById(R.id.forgetPassword_reCreatePassword);
        changePasswordButton = findViewById(R.id.forgetPassword_changePasswordButton);
    }
}