package com.example.middlemanv007.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.middlemanv007.AuthorizedCompanyDto;
import com.example.middlemanv007.R;
import com.example.middlemanv007.VendorRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class GalleryFragment extends Fragment {


    private View root;
    private RecyclerView recyclerView;
    private VendorRecyclerAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView=root.findViewById(R.id.vendors_recView);

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        FirebaseRecyclerOptions<AuthorizedCompanyDto> options
                = new FirebaseRecyclerOptions.Builder<AuthorizedCompanyDto>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Vendor"), AuthorizedCompanyDto.class)
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

    /**
     * onStop() method is used to set listener for adapter.
     */
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}