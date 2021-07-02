package com.example.middlemanv007;

import androidx.appcompat.app.AppCompatActivity;

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

public class AuthorizedCreateRequest extends AppCompatActivity {

    Button companyRequestBtn, vendorRequestBtn;
    RelativeLayout companyRelLayout, vendorRelLayout;
    TextInputLayout companyMaterialType, companyStockNeeded, companyRequiredWithIn, companyContactNo, vendorMaterialType, vendorStockAvailable, vendorExpireWithIn, vendorContactNo;
    String userName, companyMaterialTypeText, companyStockNeededText, companyRequiredWithInText, companyContactNoText, vendorMaterialTypeText, vendorStockAvailableText, vendorExpireWithInText, vendorContactNoText;
    RadioButton companyRadioBtn, vendorRadioBtn;
    RadioGroup radioGroup;

    DatabaseReference reference, reference1, reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized_create_request);
        initializeViews();

        try {
            userName = getIntent().getStringExtra("userName");
        } catch (Exception e) {
            userName = null;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (companyRadioBtn.isChecked()) {
                    companyRelLayout.setVisibility(View.VISIBLE);
                    vendorRelLayout.setVisibility(View.GONE);
                } else if (vendorRadioBtn.isChecked()) {
                    vendorRelLayout.setVisibility(View.VISIBLE);
                    companyRelLayout.setVisibility(View.GONE);
                }
            }
        });

        vendorRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeVendorStrings();

                if (validateVendorMaterialType()) {
                    if (validateVendorStocksAvailable()) {
                        if (validateVendorExpireDate()) {
                            if (validateVendorContactNo()) {
                                reference = FirebaseDatabase.getInstance().getReference("SalesMela");
                                AuthorizedVendorRequestDto vendorDto = new AuthorizedVendorRequestDto(vendorMaterialTypeText, vendorStockAvailableText, vendorExpireWithInText, vendorContactNoText);
                                reference.child(userName).setValue(vendorDto);
                                reference1 = FirebaseDatabase.getInstance().getReference("Requests");
                                reference1.child("Vendor").child(userName).setValue(vendorDto);
                                Toast.makeText(AuthorizedCreateRequest.this, "Vendor Success", Toast.LENGTH_SHORT).show();
                            } else {
                                validateVendorContactNo();
                            }
                        } else {
                            validateVendorExpireDate();
                        }
                    } else {
                        validateVendorStocksAvailable();
                    }
                } else {
                    validateVendorMaterialType();
                }

            }
        });

        companyRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeCompanyStrings();
                if (validateCompanyMaterialType()) {
                    if (validateCompanyStocksNeeded()) {
                        if (validateCompanyRequiredWithIn()) {
                            if (validateCompanyContactNo()) {
                                reference = FirebaseDatabase.getInstance().getReference("SalesMela");
                                AuthorizedCompanyRequestDto companyDto = new AuthorizedCompanyRequestDto(companyMaterialTypeText, companyStockNeededText, companyRequiredWithInText, companyContactNoText);
                                reference.child(userName).setValue(companyDto);
                                reference2 = FirebaseDatabase.getInstance().getReference("Requests");
                                reference1.child("Company").child(userName).setValue(companyDto);
                                Toast.makeText(AuthorizedCreateRequest.this, "Company Success", Toast.LENGTH_SHORT).show();
                            } else {
                                validateCompanyContactNo();
                            }
                        } else {
                            validateCompanyRequiredWithIn();
                        }
                    } else {
                        validateCompanyStocksNeeded();
                    }
                } else {
                    validateCompanyMaterialType();
                }

            }
        });
    }

    private boolean validateVendorMaterialType() {
        if (vendorMaterialTypeText.equals("")) {

            vendorMaterialType.setError("Enter Material Type");
            vendorMaterialType.setErrorEnabled(true);
            return false;
        } else {
            vendorMaterialType.setError(null);
            vendorMaterialType.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateVendorStocksAvailable() {
        if (vendorStockAvailableText.equals("")) {

            vendorStockAvailable.setError("Enter Stocks Available");
            vendorStockAvailable.setErrorEnabled(true);
            return false;
        } else {
            vendorStockAvailable.setError(null);
            vendorStockAvailable.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateVendorExpireDate() {
        if (vendorExpireWithInText.equals("")) {

            vendorExpireWithIn.setError("Enter Expire WithIn Date");
            vendorExpireWithIn.setErrorEnabled(true);
            return false;
        } else {
            vendorExpireWithIn.setError(null);
            vendorExpireWithIn.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateVendorContactNo() {
        if (vendorContactNoText.equals("")) {

            vendorContactNo.setError("Enter Contact No");
            vendorContactNo.setErrorEnabled(true);
            return false;
        } else {
            vendorContactNo.setError(null);
            vendorContactNo.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateCompanyMaterialType() {

        if (companyMaterialTypeText.equals("")) {
            companyMaterialType.setErrorEnabled(true);
            companyMaterialType.setError("Enter Material Type Needed");
            return false;
        } else {
            companyMaterialType.setErrorEnabled(false);
            companyMaterialType.setError(null);
            return true;
        }
    }

    private boolean validateCompanyStocksNeeded() {
        if (companyStockNeededText.equals("")) {
            companyStockNeeded.setErrorEnabled(true);
            companyStockNeeded.setError("Enter Stocks Needed in kg");
            return false;
        } else {
            companyStockNeeded.setErrorEnabled(false);
            companyStockNeeded.setError(null);
            return true;
        }
    }

    private boolean validateCompanyRequiredWithIn() {
        if (companyRequiredWithInText.equals("")) {
            companyRequiredWithIn.setErrorEnabled(true);
            companyRequiredWithIn.setError("Enter Required Date");
            return false;
        } else {
            companyRequiredWithIn.setErrorEnabled(false);
            companyRequiredWithIn.setError(null);
            return true;
        }
    }

    private boolean validateCompanyContactNo() {
        if (companyContactNoText.equals("")) {
            companyContactNo.setErrorEnabled(true);
            companyContactNo.setError("Enter Contact No");
            return false;
        } else {
            companyContactNo.setErrorEnabled(false);
            companyContactNo.setError(null);
            return true;
        }
    }

    private void initializeViews() {
        companyRelLayout = findViewById(R.id.authorizedCompanyRelLayout);
        vendorRelLayout = findViewById(R.id.authorizedVendorRelLayout);
        radioGroup = findViewById(R.id.authorizedRequestRadioGrp);

        companyMaterialType = findViewById(R.id.authorizedCreateRequest_materialType);
        companyStockNeeded = findViewById(R.id.authorizedCreateRequest_stocksNeeded);
        companyRequiredWithIn = findViewById(R.id.authorizedCreateRequest_requiredDate);
        companyContactNo = findViewById(R.id.authorizedCreateRequest_contactNo);
        companyRequestBtn = findViewById(R.id.authorizedCreateRequest_companyCreateRequest);
        companyRadioBtn = findViewById(R.id.authorizedRequestCompany);


        vendorMaterialType = findViewById(R.id.authorizedCreateRequest_VendorMaterialType);
        vendorStockAvailable = findViewById(R.id.authorizedCreateRequest_vendorStocksAvailable);
        vendorExpireWithIn = findViewById(R.id.authorizedCreateRequest_vendorExpireDate);
        vendorContactNo = findViewById(R.id.authorizedCreateRequest_vendorContactNo);
        vendorRequestBtn = findViewById(R.id.authorizedCreateRequest_vendorCreateRequestBtn);
        vendorRadioBtn = findViewById(R.id.authorizedRequestVendor);
    }

    private void initializeCompanyStrings() {
        companyMaterialTypeText = companyMaterialType.getEditText().getText().toString();
        companyStockNeededText = companyStockNeeded.getEditText().getText().toString();
        companyRequiredWithInText = companyRequiredWithIn.getEditText().getText().toString();
        companyContactNoText = companyContactNo.getEditText().getText().toString();
    }

    private void initializeVendorStrings() {
        vendorMaterialTypeText = vendorMaterialType.getEditText().getText().toString();
        vendorStockAvailableText = vendorStockAvailable.getEditText().getText().toString();
        vendorExpireWithInText = vendorExpireWithIn.getEditText().getText().toString();
        vendorContactNoText = vendorContactNo.getEditText().getText().toString();
    }
}