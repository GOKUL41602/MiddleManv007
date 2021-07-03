package com.example.middlemanv007;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AuthorizedUserProfile extends AppCompatActivity {

    private RelativeLayout companyRelLayout, vendorRelLayout;
    private TextInputLayout companyName, companyResourceType, companyLicenceNo, companyEmail, companyEstablishmentYear, vendorName, vendorResourceType, vendorEmail, vendorStartYear;
    private Button companyUpdateButton, vendorUpdateButton;
    private String userName, userType, companyNameText, companyResourceTypeText, companyLicenceNoText, companyEmailText, companyEstablishmentYearText, vendorNameText, vendorResourceTypeText, vendorEmailText, vendorStartYearText;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized_user_profile);
        try {
            userName = getIntent().getStringExtra("userName");
        } catch (Exception e) {
            userName = null;
        }
        getUserTypeAndLoadDetails();
        initializeViews();

    }

    private void getUserTypeAndLoadDetails() {
        reference = FirebaseDatabase.getInstance().getReference("AuthorizedUsersDto");
        Query query = reference.orderByChild("userName").startAt(userName).endAt(userName + "\uf8ff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    userType = snapshot.child(userName).child("userType").getValue(String.class);
                    if (userType.equals("Vendor")) {
                        vendorEmailText = snapshot.child(userName).child("email").getValue(String.class);
                        vendorNameText = snapshot.child(userName).child("vendorName").getValue(String.class);
                        vendorResourceTypeText = snapshot.child(userName).child("vendorSourceType").getValue(String.class);
                        vendorStartYearText = snapshot.child(userName).child("vendorStartYear").getValue(String.class);
                        vendorRelLayout.setVisibility(View.VISIBLE);
                        companyRelLayout.setVisibility(View.GONE);
                        loadVendorDetails(vendorEmailText, vendorNameText, vendorResourceTypeText, vendorStartYearText);
                    } else if (userType.equals("Company")) {
                        companyEmailText = snapshot.child(userName).child("email").getValue(String.class);
                        companyResourceTypeText = snapshot.child(userName).child("resourceType").getValue(String.class);
                        companyNameText = snapshot.child(userName).child("companyName").getValue(String.class);
                        companyEstablishmentYearText = snapshot.child(userName).child("yearOfEstablish").getValue(String.class);
                        companyLicenceNoText = snapshot.child(userName).child("companyLicenceNo").getValue(String.class);
                        companyRelLayout.setVisibility(View.VISIBLE);
                        vendorRelLayout.setVisibility(View.GONE);
                        loadCompanyDetails(companyEmailText, companyResourceTypeText, companyNameText, companyEstablishmentYearText, companyLicenceNoText);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadCompanyDetails(String companyEmailText, String companyResourceTypeText, String companyNameText, String companyEstablishmentYearText, String companyLicenceNoText) {
        companyEmail.getEditText().setText(companyEmailText);
        companyResourceType.getEditText().setText(companyResourceTypeText);
        companyName.getEditText().setText(companyNameText);
        companyEstablishmentYear.getEditText().setText(companyEstablishmentYearText);
        companyLicenceNo.getEditText().setText(companyLicenceNoText);
    }

    private void loadVendorDetails(String vendorEmailText, String vendorNameText, String vendorResourceTypeText, String vendorStartYearText) {
        vendorEmail.getEditText().setText(vendorEmailText);
        vendorName.getEditText().setText(vendorNameText);
        vendorResourceType.getEditText().setText(vendorResourceTypeText);
        vendorStartYear.getEditText().setText(vendorStartYearText);
    }

    private void initializeString() {
        companyNameText = companyName.getEditText().getText().toString();
        companyResourceTypeText = companyResourceType.getEditText().getText().toString();
        companyLicenceNoText = companyLicenceNo.getEditText().getText().toString();
        companyEmailText = companyEmail.getEditText().getText().toString();
        companyEstablishmentYearText = companyEstablishmentYear.getEditText().getText().toString();

        vendorNameText = vendorName.getEditText().getText().toString();
        vendorResourceTypeText = vendorResourceType.getEditText().getText().toString();
        vendorEmailText = vendorEmail.getEditText().getText().toString();
        vendorStartYearText = vendorStartYear.getEditText().getText().toString();

    }

    private void initializeViews() {
        companyRelLayout = findViewById(R.id.authorizedUserProfile_companyRelLayout);
        vendorRelLayout = findViewById(R.id.authorizedUserProfile_vendorRelLayout);

        companyName = findViewById(R.id.authorizedUserProfile_companyName);
        companyResourceType = findViewById(R.id.authorizedUserProfile_companyMaterialType);
        companyLicenceNo = findViewById(R.id.authorizedUserProfile_companyLicenceNo);
        companyEmail = findViewById(R.id.authorizedUserProfile_companyEmail);
        companyEstablishmentYear = findViewById(R.id.authorizedUserProfile_companyEstablishYear);


        vendorName = findViewById(R.id.authorizedUserProfile_vendorName);
        vendorResourceType = findViewById(R.id.authorizedUserProfile_vendorSourceType);
        vendorEmail = findViewById(R.id.authorizedUserProfile_vendorEmail);
        vendorStartYear = findViewById(R.id.authorizedUserProfile_vendorStartYear);

        companyUpdateButton = findViewById(R.id.authorizedUserProfile_companyUpdateButton);
        vendorUpdateButton = findViewById(R.id.authorizedUserProfile_vendorUpdateButton);
    }
}