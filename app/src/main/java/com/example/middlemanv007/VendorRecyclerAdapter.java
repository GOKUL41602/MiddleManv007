package com.example.middlemanv007;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class VendorRecyclerAdapter extends FirebaseRecyclerAdapter<AuthorizedUserRegistrationDto,VendorRecyclerAdapter.ViewHolder> {

    public VendorRecyclerAdapter(@NonNull FirebaseRecyclerOptions<AuthorizedUserRegistrationDto> options, Context context) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull AuthorizedUserRegistrationDto model) {
        holder.vendorName.setText(model.getUserName());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_format_layout,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView cardView;
        private TextView vendorName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews(itemView);
        }

        private void initializeViews(View view)
        {
            cardView=view.findViewById(R.id.vendor_cardView);
            vendorName=view.findViewById(R.id.vendor_cardView_name);
        }
    }
}
