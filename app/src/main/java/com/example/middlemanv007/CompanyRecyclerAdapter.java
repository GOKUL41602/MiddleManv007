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

public class CompanyRecyclerAdapter extends FirebaseRecyclerAdapter<AuthorizedCompanyDto, CompanyRecyclerAdapter.ViewHolder> {

    Context context;
    public CompanyRecyclerAdapter(@NonNull FirebaseRecyclerOptions<AuthorizedCompanyDto> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull AuthorizedCompanyDto model) {
        holder.companyName.setText(model.getUserName());
        holder.establishmentYear.setText(model.getYearOfEstablish());
        holder.sourceType.setText(model.getResourceType());
        holder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String phone = model.getPhoneNo();
                String phoneText="+91"+phone;
                intent.setData(Uri.parse("tel:" + phoneText));
                holder.context.startActivity(intent);
            }
        });
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
        private TextView companyName,sourceType,establishmentYear;
        private Context context;
        private ImageView callBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews(itemView);
            context=itemView.getContext();
        }

        private void initializeViews(View view)
        {
            cardView=view.findViewById(R.id.company_cardView);
            companyName=view.findViewById(R.id.company_cardView_name);
            sourceType=view.findViewById(R.id.company_cardView_sourceType);
            establishmentYear=view.findViewById(R.id.company_cardView_establishmentYear);
            callBtn=view.findViewById(R.id.company_cardView_callBtn);
        }
    }
}
