package com.example.middlemanv007;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class AuthorizedUserProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

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
        initializeViews();
        getUserTypeAndLoadDetails();


        drawerLayout = findViewById(R.id.authorizedUserProfile_design_navigation_view);

        Toolbar toolbar = findViewById(R.id.authorizedUserProfile_toolbar);

        NavigationView navigationView = findViewById(R.id.authorizedUserProfile_nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_draw_close);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();


        companyUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeString();
                if (validateCompanyName()) {
                    if (validateCompanyResourceType()) {
                        if (validateCompanyLicenceNo()) {
                            if (validateCompanyEmail()) {
                                if (validateCompanyEstablishYear()) {
                                    reference = FirebaseDatabase.getInstance().getReference("AuthorizedUsersDto");
                                    Query query = reference.orderByChild("userName").startAt(userName).endAt(userName + "\uf8ff");
                                    query.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                                            if (snapshot.exists()) {
                                                reference.child(userName).child("companyName").setValue(companyNameText);
                                                reference.child(userName).child("resourceType").setValue(companyResourceTypeText);
                                                reference.child(userName).child("email").setValue(companyEmailText);
                                                reference.child(userName).child("companyLicenceNo").setValue(companyLicenceNoText);
                                                reference.child(userName).child("yearOfEstablish").setValue(companyEstablishmentYearText);
                                            }
                                        }

                                        @Override
                                        public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

                                        }

                                        @Override
                                        public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

                                        }

                                        @Override
                                        public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

                                        }

                                        @Override
                                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                        }
                                    });

                                } else {
                                    validateCompanyEstablishYear();
                                }
                            } else {
                                validateCompanyEmail();
                            }
                        } else {
                            validateCompanyLicenceNo();
                        }
                    } else {
                        validateCompanyResourceType();
                    }
                } else {
                    validateCompanyName();
                }
            }
        });
        vendorUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeString();
                if (validateVendorName()) {
                    if (validateVendorResourceType()) {
                        if (validateVendorEmail()) {
                            if (validateVendorStartYear()) {
                                reference = FirebaseDatabase.getInstance().getReference("AuthorizedUsersDto");
                                Query query = reference.orderByChild("userName").startAt(userName).endAt(userName + "\uf8ff");
                                query.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                                        if (snapshot.exists()) {
                                            reference.child(userName).child("vendorName").setValue(vendorNameText);
                                            reference.child(userName).child("email").setValue(vendorEmailText);
                                            reference.child(userName).child("vendorSourceType").setValue(vendorResourceTypeText);
                                            reference.child(userName).child("vendorStartYear").setValue(vendorStartYearText);
                                        }
                                    }

                                    @Override
                                    public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

                                    }

                                    @Override
                                    public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

                                    }

                                    @Override
                                    public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });
                            } else {
                                validateVendorStartYear();
                            }
                        } else {
                            validateVendorEmail();
                        }
                    } else {
                        validateVendorResourceType();
                    }
                } else {
                    validateVendorName();
                }

            }
        });

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

    private boolean validateVendorName() {
        if (vendorNameText.equals("")) {
            vendorName.setErrorEnabled(true);
            vendorName.setError("Enter vendor Name");
            return false;
        } else {
            vendorName.setErrorEnabled(false);
            vendorName.setError(null);
            return true;
        }
    }

    private boolean validateVendorEmail() {
        if (vendorEmailText.equals("")) {
            vendorEmail.setErrorEnabled(true);
            vendorEmail.setError("Enter vendor Email");
            return false;
        } else {
            vendorEmail.setErrorEnabled(false);
            vendorEmail.setError(null);
            return true;
        }
    }

    private boolean validateVendorResourceType() {
        if (vendorResourceTypeText.equals("")) {
            vendorResourceType.setErrorEnabled(true);
            vendorResourceType.setError("Enter vendor ResourceType");
            return false;
        } else {
            vendorResourceType.setErrorEnabled(false);
            vendorResourceType.setError(null);
            return true;
        }
    }

    private boolean validateVendorStartYear() {
        if (vendorStartYearText.equals("")) {
            vendorStartYear.setErrorEnabled(true);
            vendorStartYear.setError("Enter vendor StartYear");
            return false;
        } else {
            vendorStartYear.setErrorEnabled(false);
            vendorStartYear.setError(null);
            return true;
        }
    }

    private boolean validateCompanyName() {
        if (companyNameText.equals("")) {
            companyName.setErrorEnabled(true);
            companyName.setError("Enter Company Name");
            return false;
        } else {
            companyName.setErrorEnabled(false);
            companyName.setError(null);
            return true;
        }
    }

    private boolean validateCompanyResourceType() {
        if (companyResourceTypeText.equals("")) {
            companyResourceType.setErrorEnabled(true);
            companyResourceType.setError("Enter Company Resource Tpye");
            return false;
        } else {
            companyResourceType.setErrorEnabled(false);
            companyResourceType.setError(null);
            return true;
        }
    }

    private boolean validateCompanyLicenceNo() {
        if (companyLicenceNoText.equals("")) {
            companyLicenceNo.setErrorEnabled(true);
            companyLicenceNo.setError("Enter Company Licence No");
            return false;
        } else {
            companyLicenceNo.setErrorEnabled(false);
            companyLicenceNo.setError(null);
            return true;
        }
    }

    private boolean validateCompanyEmail() {
        if (companyEmailText.equals("")) {
            companyEmail.setErrorEnabled(true);
            companyEmail.setError("Enter Company Email");
            return false;
        } else {
            companyEmail.setErrorEnabled(false);
            companyEmail.setError(null);
            return true;
        }
    }

    private boolean validateCompanyEstablishYear() {
        if (companyEstablishmentYearText.equals("")) {
            companyEstablishmentYear.setErrorEnabled(true);
            companyEstablishmentYear.setError("Enter Company Established Year");
            return false;
        } else {
            companyEstablishmentYear.setErrorEnabled(false);
            companyEstablishmentYear.setError(null);
            return true;
        }
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_updateProfile:
                Intent intent = new Intent(AuthorizedUserProfile.this, AuthorizedUserProfile.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userType", userType);
                startActivity(intent);
                AuthorizedUserProfile.this.finish();
                break;
            case R.id.nav_showRequests:
                Intent intent1 = new Intent(AuthorizedUserProfile.this, AuthorizedUserRequestsView.class);
                intent1.putExtra("userName", userName);
                intent1.putExtra("userType", userType);
                startActivity(intent1);
                AuthorizedUserProfile.this.finish();
                break;
            case R.id.nav_createRequest:
                Intent intent2 = new Intent(AuthorizedUserProfile.this, AuthorizedCreateRequest.class);
                intent2.putExtra("userName", userName);
                intent2.putExtra("userType", userType);
                startActivity(intent2);
                AuthorizedUserProfile.this.finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}