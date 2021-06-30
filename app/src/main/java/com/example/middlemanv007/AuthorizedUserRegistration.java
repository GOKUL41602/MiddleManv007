package com.example.middlemanv007;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthorizedUserRegistration extends AppCompatActivity {

    TextInputLayout email, companyName, companyRegisterNo, password, reTypePassword, phoneNo;
    RadioGroup userType;
    Button registerButton;
    RelativeLayout relativeLayout;
    String emailText, companyNameText, companyRegisterNoText, passwordText, phoneNoText, userTypeText, reTypePasswordText,userName;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized_user_registration);

        try {
            userName=getIntent().getStringExtra("userName");
        }
        catch (Exception e)
        {
            userName=null;
        }
        initializeViews();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeStrings();
                if (validateEmail()) {
                    if (validateCompanyName()) {
                        if (validateCompanyRegisterNo()) {
                            if (validatePhoneNo()) {
                                if (validatePassword()) {
                                    if (validateReEnterPassword()) {
                                        if (verifyPassword()) {
                                            if (validateUserType()) {
                                                AuthorizedUserRegistrationDto authDto=new AuthorizedUserRegistrationDto(emailText,userTypeText,companyNameText,companyRegisterNoText,reTypePasswordText,phoneNoText);
                                                reference= FirebaseDatabase.getInstance().getReference("AuthorizedUsersDto");
                                                reference.child(userName).setValue(authDto);
                                                Snackbar.make(relativeLayout, "Registration Successful", Snackbar.LENGTH_LONG).setAction("LOGIN", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Intent intent=new Intent(AuthorizedUserRegistration.this,AuthorizedUserLogin.class);
                                                        startActivity(intent);
                                                        AuthorizedUserRegistration.this.finish();
                                                    }
                                                }).show();
                                            } else {
                                                validateUserType();
                                            }
                                        } else {
                                            verifyPassword();
                                        }
                                    } else {
                                        validateReEnterPassword();
                                    }
                                } else {
                                    validatePassword();
                                }
                            } else {
                                validatePhoneNo();
                            }
                        } else {
                            validateCompanyRegisterNo();
                        }
                    } else {
                        validateCompanyName();
                    }
                } else {
                    validateEmail();
                }
            }
        });

    }


    private boolean validateEmail() {
        if (emailText.equals("")) {
            email.setError("Enter E-mail address");
            email.setErrorEnabled(true);
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateCompanyName() {
        if (companyNameText.equals("")) {
            companyName.setError("Enter Company Name");
            companyName.setErrorEnabled(true);
            return false;
        } else {
            companyName.setError(null);
            companyName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateCompanyRegisterNo() {
        if (companyRegisterNoText.equals("")) {
            companyRegisterNo.setError("Enter Company Registration No");
            companyRegisterNo.setErrorEnabled(true);
            return false;
        } else {
            companyRegisterNo.setError(null);
            companyRegisterNo.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhoneNo() {
        if (phoneNoText.equals("")) {
            phoneNo.setError("Enter Phone No");
            phoneNo.setErrorEnabled(true);
            return false;
        } else {
            phoneNo.setError(null);
            phoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        if (passwordText.equals("")) {
            password.setError("Enter Password");
            password.setErrorEnabled(true);
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateReEnterPassword() {
        if (reTypePasswordText.equals("")) {
            reTypePassword.setError("Enter Password");
            reTypePassword.setErrorEnabled(true);
            return false;
        } else {
            reTypePassword.setError(null);
            reTypePassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean verifyPassword() {
        if (reTypePasswordText.equals(passwordText)) {
            reTypePassword.setError(null);
            reTypePassword.setErrorEnabled(false);
            return true;
        } else {
            reTypePassword.setError("Password doesn't match");
            reTypePassword.setErrorEnabled(true);
            return false;
        }
    }

    private boolean validateUserType() {
        getUserType();
        if (userTypeText.isEmpty()) {
            Toast.makeText(this, "Please Select User Type", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void getUserType() {
        if (userType.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please choose user Type", Toast.LENGTH_SHORT).show();
        } else {
            int radioButton = userType.getCheckedRadioButtonId();
            RadioButton type = findViewById(radioButton);
            userTypeText = type.getText().toString();

        }
    }

    private void initializeStrings() {
        emailText = email.getEditText().getText().toString();
        companyNameText = companyName.getEditText().getText().toString();
        companyRegisterNoText = companyRegisterNo.getEditText().getText().toString();
        passwordText = password.getEditText().getText().toString();
        reTypePasswordText = reTypePassword.getEditText().getText().toString();
        phoneNoText = phoneNo.getEditText().getText().toString();
    }

    private void initializeViews() {
        email = findViewById(R.id.authorizedRegisterPage_email);
        companyName = findViewById(R.id.authorizedRegisterPage_companyName);
        companyRegisterNo = findViewById(R.id.authorizedRegisterPage_companyRegisterNo);
        password = findViewById(R.id.authorizedRegisterPage_password);
        reTypePassword = findViewById(R.id.authorizedRegisterPage_reEnterPassword);
        phoneNo = findViewById(R.id.authorizedRegisterPage_phoneNo);
        registerButton = findViewById(R.id.authorizedRegisterPage_registerButton);
        userType = findViewById(R.id.authorizedRegisterPage_radioGroup);
        relativeLayout = findViewById(R.id.authorizedRegisterPage_relLayout);
    }
}