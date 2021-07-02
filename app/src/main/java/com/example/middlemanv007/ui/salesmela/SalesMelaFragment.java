package com.example.middlemanv007.ui.salesmela;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.middlemanv007.AuthorizedCompanyDto;
import com.example.middlemanv007.CompanyRecyclerAdapter;
import com.example.middlemanv007.R;
import com.example.middlemanv007.SalesMelaDto;
import com.example.middlemanv007.SalesMelaRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SalesMelaFragment extends Fragment {


    private RecyclerView recyclerView;
    private SalesMelaRecyclerAdapter adapter;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_sales_mela, container, false);
        recyclerView = root.findViewById(R.id.salesmela_recView);

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        FirebaseRecyclerOptions<SalesMelaDto> options
                = new FirebaseRecyclerOptions.Builder<SalesMelaDto>()
                .setQuery(FirebaseDatabase.getInstance().getReference("SalesMela"), SalesMelaDto.class)
                .build();
        adapter = new SalesMelaRecyclerAdapter(options, root.getContext());
        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(false);
        return root;
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