
package com.example.middlemanv007;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class AuthorizedUserRequestsView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private AuthorizedUserRequestsViewRecAdapter adapter;
    private String userName, userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized_user_requests_view);

        drawerLayout = findViewById(R.id.authorizedUserRequests_design_navigation_view);

        Toolbar toolbar = findViewById(R.id.authorizedUserRequests_toolbar);

        NavigationView navigationView = findViewById(R.id.authorizedUserRequests_nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_draw_close);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        try {
            userName = getIntent().getStringExtra("userName");
            userType = getIntent().getStringExtra("userType");

        } catch (Exception e) {
            userName = null;
        }
        recyclerView = findViewById(R.id.authorizedUserRequestsViewRecView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<SalesMelaDto> options
                = new FirebaseRecyclerOptions.Builder<SalesMelaDto>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Requests/" + userName), SalesMelaDto.class)
                .build();
        adapter = new AuthorizedUserRequestsViewRecAdapter(options, userType);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_updateProfile:
                Intent intent = new Intent(AuthorizedUserRequestsView.this, AuthorizedUserProfile.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userType", userType);
                startActivity(intent);
                AuthorizedUserRequestsView.this.finish();
                break;
            case R.id.nav_showRequests:
                Intent intent1 = new Intent(AuthorizedUserRequestsView.this, AuthorizedUserRequestsView.class);
                intent1.putExtra("userName", userName);
                intent1.putExtra("userType", userType);
                startActivity(intent1);
                AuthorizedUserRequestsView.this.finish();
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}