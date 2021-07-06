package com.example.middlemanv007;

import android.app.Activity;
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

public class SalesMelaRecyclerAdapter extends FirebaseRecyclerAdapter<SalesMelaDto, SalesMelaRecyclerAdapter.ViewHolder> {


    public SalesMelaRecyclerAdapter(@NonNull FirebaseRecyclerOptions<SalesMelaDto> options, Context context) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull SalesMelaDto model) {
        if(model.getUserType().equals("Vendor"))
        {
            holder.vendorCardView.setVisibility(View.VISIBLE);
            holder.companyCardView.setVisibility(View.GONE);
            holder.vendorType.setText(model.getUserType());
            holder.vendorMaterialType.setText(model.getVendorMaterialType());
            holder.vendorStocksAvailable.setText(model.getVendorStocksAvailable());
            holder.vendorExpireWithIn.setText(model.getVendorExpireWithIn());
            holder.vendorContactNo.setText(model.getVendorContactNo());


        }
        if(model.getUserType().equals("Company"))
        {
            holder.companyCardView.setVisibility(View.VISIBLE);
            holder.vendorCardView.setVisibility(View.GONE);
            holder.companyMaterialType.setText(model.getCompanyMaterialType());
            holder.companyType.setText(model.getUserType());
            holder.companyStocksNeeded.setText(model.getCompanyStocksNeeded());
            holder.companyRequiredWithIn.setText(model.getCompanyRequiredWithIn());
            holder.companyContactNo.setText(model.getCompanyContactNo());

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_mela_layout, parent, false);
        return new SalesMelaRecyclerAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Context context;
        private CardView vendorCardView, companyCardView;
        private ImageView companyCallBtn,vendorCallBtn;
        private TextView vendorType,companyType,vendorMaterialType, vendorStocksAvailable, vendorExpireWithIn, vendorContactNo, companyMaterialType, companyStocksNeeded, companyRequiredWithIn, companyContactNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews(itemView);
            context=itemView.getContext();
        }

        private void initializeViews(View view) {
            vendorMaterialType = view.findViewById(R.id.vendorMaterialType);
            vendorType=view.findViewById(R.id.vendorType);
            companyType=view.findViewById(R.id.companyType);
            vendorStocksAvailable = view.findViewById(R.id.vendorStockAvailable);
            vendorExpireWithIn = view.findViewById(R.id.vendorSoldWithDate);
            vendorContactNo = view.findViewById(R.id.vendorContactNo);
            companyMaterialType = view.findViewById(R.id.companyMaterialType);
            companyStocksNeeded = view.findViewById(R.id.companyStockNeeded);
            companyRequiredWithIn = view.findViewById(R.id.companyRequiredWithIn);
            companyContactNo = view.findViewById(R.id.companyContactNo);
            vendorCardView = view.findViewById(R.id.sales_mela_vendorCardView);
            companyCardView = view.findViewById(R.id.sales_mela_companyCardView);

            companyCallBtn=view.findViewById(R.id.company_cardView_callBtn);
            vendorCallBtn=view.findViewById(R.id.vendor_cardView_callBtn);

        }
    }
}
