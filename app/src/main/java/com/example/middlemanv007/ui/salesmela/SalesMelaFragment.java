package com.example.middlemanv007.ui.salesmela;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.middlemanv007.R;

public class SalesMelaFragment extends Fragment {


    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_sales_mela, container, false);

        return root;
    }
}