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

public class VendorRecyclerAdapter extends FirebaseRecyclerAdapter<AuthorizedVendorDto, VendorRecyclerAdapter.ViewHolder> {

    public VendorRecyclerAdapter(@NonNull FirebaseRecyclerOptions<AuthorizedVendorDto> options, Context context) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull AuthorizedVendorDto model) {

        holder.vendorName.setText(model.getUserName());
        holder.cultivationYear.setText(model.getVendorStartYear());
        holder.sourceType.setText(model.getVendorSourceType());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_format_layout,parent,false);
        return new VendorRecyclerAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView cardView;
        private TextView vendorName,sourceType,cultivationYear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews(itemView);
        }

        private void initializeViews(View view)
        {
            cardView=view.findViewById(R.id.vendor_cardView);
            vendorName=view.findViewById(R.id.vendor_cardView_name);
            sourceType=view.findViewById(R.id.vendor_cardView_sourceType);
            cultivationYear=view.findViewById(R.id.vendor_cardView_CultivationStartYear);
        }
    }
}
