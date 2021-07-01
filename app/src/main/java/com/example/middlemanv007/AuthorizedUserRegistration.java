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

    TextInputLayout email, companyName, companyRegisterNo, password, reTypePassword, phoneNo, sourceType, establishmentyear;
    TextInputLayout vendorEmail, vendorName, vendorPassword, vendorReEnterPassword, vendorPhoneNo, vendorSourceType, vendorStartYear;
    RadioButton companyBtn, vendorBtn;
    RadioGroup radioGroup;
    Button registerButton, vendorRegisterBtn;
    RelativeLayout relativeLayout, vendorRelLayout, companyRelLayout;
    String vendorEmailText, vendorNameText, vendorPasswordText, vendorReEnterPasswordText, vendorPhoneNoText, vendorSourceTypeText, vendorStartYearText;
    String emailText, companyNameText, companyRegisterNoText, passwordText, phoneNoText, userTypeText, reTypePasswordText, userName, establishmentYearText, sourceTypeText;
    DatabaseReference reference, reference1, reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized_user_registration);

        try {
            userName = getIntent().getStringExtra("userName");
        } catch (Exception e) {
            userName = null;
        }
        initializeViews();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (companyBtn.isChecked()) {
                    companyRelLayout.setVisibility(View.VISIBLE);
                    vendorRelLayout.setVisibility(View.GONE);
                } else if (vendorBtn.isChecked()) {
                    vendorRelLayout.setVisibility(View.VISIBLE);
                    companyRelLayout.setVisibility(View.GONE);
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeCompanyStrings();
                if (validateEmail()) {
                    if (validateCompanyName()) {
                        if (validateCompanyRegisterNo()) {
                            if (validatePhoneNo()) {
                                if (validateEstablishmentYear()) {
                                    if (validateSourceType()) {
                                        if (validatePassword()) {
                                            if (validateReEnterPassword()) {
                                                if (verifyPassword()) {
                                                    if (validateUserType()) {
                                                        AuthorizedCompanyDto authCompanyDto = new AuthorizedCompanyDto(emailText, userTypeText, companyNameText, companyRegisterNoText, passwordText, phoneNoText, userName, establishmentYearText, sourceTypeText);
                                                        reference = FirebaseDatabase.getInstance().getReference("AuthorizedUsersDto");
                                                        reference.child(userName).setValue(authCompanyDto);
                                                        DatabaseReference dnc = FirebaseDatabase.getInstance().getReference("Company");
                                                        dnc.child(userName).setValue(authCompanyDto);
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
                                        validateSourceType();
                                    }
                                } else {
                                    validateEstablishmentYear();
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

        vendorRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeVendorStrings();
                if (validateVendorEmail()) {
                    if (validateVendorName()) {
                        if (validateVendorPhoneNo()) {
                            if (validateVendorStartYear()) {
                                if (validateVendorSourceType()) {
                                    if (validateVendorPassword()) {
                                        if (validateVendorReEnterPassword()) {
                                            if (verifyVendorPassword()) {
                                                if (validateUserType()) {
                                                    AuthorizedVendorDto authVendorDto = new AuthorizedVendorDto(vendorNameText, vendorEmailText, vendorPhoneNoText, vendorPasswordText, vendorStartYearText, vendorSourceTypeText, userName, userTypeText);
                                                    reference = FirebaseDatabase.getInstance().getReference("AuthorizedUsersDto");
                                                    reference.child(userName).setValue(authVendorDto);
                                                    DatabaseReference dfc = FirebaseDatabase.getInstance().getReference("Vendor");
                                                    dfc.child(userName).setValue(authVendorDto);
                                                } else {
                                                    validateUserType();
                                                }
                                            } else {
                                                verifyVendorPassword();
                                            }
                                        } else {
                                            validateVendorReEnterPassword();
                                        }
                                    } else {
                                        validateVendorPassword();
                                    }
                                } else {
                                    validateVendorSourceType();
                                }
                            } else {
                                validateVendorStartYear();
                            }
                        } else {
                            validateVendorPhoneNo();
                        }
                    } else {
                        validateVendorName();
                    }
                } else {
                    validateVendorEmail();
                }
            }
        });
    }

    private boolean validateVendorName() {
        if (vendorNameText.equals("")) {
            vendorName.setError("Enter Vendor Name ");
            vendorName.setErrorEnabled(true);
            return false;
        } else {
            vendorName.setError(null);
            vendorName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateVendorEmail() {
        if (vendorEmailText.equals("")) {
            vendorEmail.setError("Enter Vendor Email ");
            vendorEmail.setErrorEnabled(true);
            return false;
        } else {
            vendorEmail.setError(null);
            vendorEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateVendorPhoneNo() {
        if (vendorPhoneNoText.equals("")) {
            vendorPhoneNo.setError("Enter PhoneNo ");
            vendorPhoneNo.setErrorEnabled(true);
            return false;
        } else {
            vendorPhoneNo.setError(null);
            vendorPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateVendorStartYear() {
        if (vendorStartYearText.equals("")) {
            vendorStartYear.setError("Enter StartYear of Cultivate ");
            vendorStartYear.setErrorEnabled(true);
            return false;
        } else {
            vendorStartYear.setError(null);
            vendorStartYear.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateVendorSourceType() {
        if (vendorSourceTypeText.equals("")) {
            vendorSourceType.setError("Enter Vendor SourceType ");
            vendorSourceType.setErrorEnabled(true);
            return false;
        } else {
            vendorSourceType.setError(null);
            vendorSourceType.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateVendorPassword() {
        if (vendorPasswordText.equals("")) {
            vendorPassword.setError("Enter Password ");
            vendorPassword.setErrorEnabled(true);
            return false;
        } else {
            vendorPassword.setError(null);
            vendorPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateVendorReEnterPassword() {
        if (vendorReEnterPasswordText.equals("")) {
            vendorReEnterPassword.setError("Enter Password ");
            vendorReEnterPassword.setErrorEnabled(true);
            return false;
        } else {
            vendorReEnterPassword.setError(null);
            vendorReEnterPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean verifyVendorPassword() {
        if (!vendorReEnterPasswordText.equals(vendorPasswordText)) {
            vendorReEnterPassword.setError("Enter Password ");
            vendorReEnterPassword.setErrorEnabled(true);
            return false;
        } else {
            vendorReEnterPassword.setError(null);
            vendorReEnterPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEstablishmentYear() {
        if (establishmentYearText.equals("")) {
            establishmentyear.setError("Enter Establishment Type");
            establishmentyear.setErrorEnabled(true);
            return false;
        } else {
            establishmentyear.setError(null);
            establishmentyear.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateSourceType() {
        if (sourceTypeText.equals("")) {
            sourceType.setError("Enter Source Type");
            sourceType.setErrorEnabled(true);
            return false;
        } else {
            sourceType.setError(null);
            sourceType.setErrorEnabled(false);
            return true;
        }
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
        if (companyBtn.isChecked()) {
            userTypeText = companyBtn.getText().toString();
            vendorRelLayout.setVisibility(View.GONE);
            companyRelLayout.setVisibility(View.VISIBLE);
        } else if (vendorBtn.isChecked()) {
            userTypeText = vendorBtn.getText().toString();
            vendorRelLayout.setVisibility(View.VISIBLE);
            companyRelLayout.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Please Select User Type", Toast.LENGTH_SHORT).show();
        }

    }

    private void initializeCompanyStrings() {
        emailText = email.getEditText().getText().toString();
        companyNameText = companyName.getEditText().getText().toString();
        companyRegisterNoText = companyRegisterNo.getEditText().getText().toString();
        passwordText = password.getEditText().getText().toString();
        reTypePasswordText = reTypePassword.getEditText().getText().toString();
        phoneNoText = phoneNo.getEditText().getText().toString();
        sourceTypeText = sourceType.getEditText().getText().toString();
        establishmentYearText = establishmentyear.getEditText().getText().toString();

    }

    private void initializeVendorStrings() {
        vendorEmailText = vendorEmail.getEditText().getText().toString();
        vendorNameText = vendorName.getEditText().getText().toString();
        vendorSourceTypeText = vendorSourceType.getEditText().getText().toString();
        vendorPasswordText = vendorPassword.getEditText().getText().toString();
        vendorReEnterPasswordText = vendorReEnterPassword.getEditText().getText().toString();
        vendorStartYearText = vendorStartYear.getEditText().getText().toString();
        vendorPhoneNoText = vendorPhoneNo.getEditText().getText().toString();
    }

    private void initializeViews() {
        email = findViewById(R.id.authorizedRegisterPage_email);
        companyName = findViewById(R.id.authorizedRegisterPage_companyName);
        companyRegisterNo = findViewById(R.id.authorizedRegisterPage_companyRegisterNo);
        password = findViewById(R.id.authorizedRegisterPage_password);
        reTypePassword = findViewById(R.id.authorizedRegisterPage_reEnterPassword);
        phoneNo = findViewById(R.id.authorizedRegisterPage_phoneNo);
        registerButton = findViewById(R.id.authorizedRegisterPage_companyRegisterButton);
        vendorRegisterBtn = findViewById(R.id.authorizedRegisterPage_vendorRegisterButton);
        companyBtn = findViewById(R.id.authorizedRegisterPage_companyRadioButton);
        vendorBtn = findViewById(R.id.authorizedRegisterPage_vendorRadioButton);
        relativeLayout = findViewById(R.id.authorizedRegisterPage_relLayout);
        sourceType = findViewById(R.id.authorizedRegisterPage_resourceType);
        establishmentyear = findViewById(R.id.authorizedRegisterPage_establishmentYear);

        vendorEmail = findViewById(R.id.authorizedRegisterPage_vendorEmail);
        vendorName = findViewById(R.id.authorizedRegisterPage_vendorName);
        vendorPhoneNo = findViewById(R.id.authorizedRegisterPage_vendorPhoneNo);
        vendorPassword = findViewById(R.id.authorizedRegisterPage_vendorPassword);
        vendorReEnterPassword = findViewById(R.id.authorizedRegisterPage_vendorReEnterPassword);
        vendorSourceType = findViewById(R.id.authorizedRegisterPage_vendorResourceType);
        vendorStartYear = findViewById(R.id.authorizedRegisterPage_vendorStartYear);

        radioGroup = findViewById(R.id.authorizedRegisterPage_radioGroup);

        vendorRelLayout = findViewById(R.id.authorizedRegisterPage_vendorRelLayout);
        companyRelLayout = findViewById(R.id.authorizedRegisterPage_companyRelLayout);

    }
}
