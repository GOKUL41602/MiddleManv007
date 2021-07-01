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

public class VendorRecyclerAdapter extends FirebaseRecyclerAdapter<AuthorizedCompanyDto,VendorRecyclerAdapter.ViewHolder> {

    public VendorRecyclerAdapter(@NonNull FirebaseRecyclerOptions<AuthorizedCompanyDto> options, Context context) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull AuthorizedCompanyDto model) {
        holder.vendorName.setText(model.getUserName());
        holder.establishmentYear.setText(model.getYearOfEstablish());
        holder.sourceType.setText(model.getResourceType());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.company_format_layout,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView cardView;
        private TextView vendorName,sourceType,establishmentYear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews(itemView);
        }

        private void initializeViews(View view)
        {
            cardView=view.findViewById(R.id.company_cardView);
            vendorName=view.findViewById(R.id.company_cardView_name);
            sourceType=view.findViewById(R.id.company_cardView_sourceType);
            establishmentYear=view.findViewById(R.id.company_cardView_establishmentYear);
        }
    }
}
