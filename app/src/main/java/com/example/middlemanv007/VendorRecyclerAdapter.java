package com.example.middlemanv007;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class VendorRecyclerAdapter extends FirebaseRecyclerAdapter<AuthorizedVendorDto, VendorRecyclerAdapter.ViewHolder> {

    Context context;
    public VendorRecyclerAdapter(@NonNull FirebaseRecyclerOptions<AuthorizedVendorDto> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull AuthorizedVendorDto model) {

        holder.vendorName.setText(model.getUserName());
        holder.cultivationYear.setText(model.getVendorStartYear());
        holder.sourceType.setText(model.getVendorSourceType());
        holder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String phone = model.getVendorPhoneNo();
                String phoneText = "+91" + phone;
                intent.setData(Uri.parse("tel:" + phoneText));
                holder.context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_format_layout, parent, false);
        return new VendorRecyclerAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private Context context;
        private ImageView callBtn;
        private TextView vendorName, sourceType, cultivationYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews(itemView);
            context = itemView.getContext();
        }

        private void initializeViews(View view) {
            cardView = view.findViewById(R.id.vendor_cardView);
            vendorName = view.findViewById(R.id.vendor_cardView_name);
            sourceType = view.findViewById(R.id.vendor_cardView_sourceType);
            callBtn = view.findViewById(R.id.vendor_cardView_callBtn);
            cultivationYear = view.findViewById(R.id.vendor_cardView_CultivationStartYear);
        }
    }
}
