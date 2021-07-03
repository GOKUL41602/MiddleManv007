
package com.example.middlemanv007;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AuthorizedUserRequestsView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AuthorizedUserRequestsViewRecAdapter adapter;
    private String userName,userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized_user_requests_view);

        try {
            userName = getIntent().getStringExtra("userName");
            userType = getIntent().getStringExtra("userType");
            Log.d("userName",userName);

        } catch (Exception e) {
            userName = null;
        }
        recyclerView = findViewById(R.id.authorizedUserRequestsViewRecView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<SalesMelaDto> options
                = new FirebaseRecyclerOptions.Builder<SalesMelaDto>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Requests/"+userName), SalesMelaDto.class)
                .build();
        adapter = new AuthorizedUserRequestsViewRecAdapter(options,userType);
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

}