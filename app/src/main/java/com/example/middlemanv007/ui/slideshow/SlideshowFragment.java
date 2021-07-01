package com.example.middlemanv007.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.middlemanv007.AuthorizedCompanyDto;
import com.example.middlemanv007.AuthorizedVendorDto;
import com.example.middlemanv007.CompanyRecyclerAdapter;
import com.example.middlemanv007.R;
import com.example.middlemanv007.VendorRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SlideshowFragment extends Fragment {

    private View root;
    private RecyclerView recyclerView;
    private VendorRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        recyclerView=root.findViewById(R.id.vendor_recView);

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        FirebaseRecyclerOptions<AuthorizedVendorDto> options
                = new FirebaseRecyclerOptions.Builder<AuthorizedVendorDto>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Vendor"), AuthorizedVendorDto.class)
                .build();
        adapter = new VendorRecyclerAdapter(options, root.getContext());
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