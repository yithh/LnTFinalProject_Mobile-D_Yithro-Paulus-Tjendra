package com.example.mathics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {
    private EditText etBalokPanjang, etBalokLebar, etBalokTinggi, etPiramidaSisi, etPiramidaTinggi, etTabungJari, etTabungTinggi;
    private TextView tvBalokResult, tvPiramidaResult, tvTabungResult;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        etBalokPanjang = view.findViewById(R.id.et_balok_panjang);
        etBalokLebar = view.findViewById(R.id.et_balok_lebar);
        etBalokTinggi = view.findViewById(R.id.et_balok_tinggi);
        etPiramidaSisi = view.findViewById(R.id.et_piramida_sisi);
        etPiramidaTinggi = view.findViewById(R.id.et_piramida_tinggi);
        etTabungJari = view.findViewById(R.id.et_tabung_jarijari);
        etTabungTinggi = view.findViewById(R.id.et_tabung_tinggi);

        Button btnBalokCalculate = view.findViewById(R.id.btn_balok_calculate);
        Button btnPiramidaCalculate = view.findViewById(R.id.btn_piramida_calculate);
        Button btnTabungCalculate = view.findViewById(R.id.btn_tabung_calculate);

        tvBalokResult = view.findViewById(R.id.tv_balok_result);
        tvPiramidaResult = view.findViewById(R.id.tv_piramida_result);
        tvTabungResult = view.findViewById(R.id.tv_tabung_result);

        btnBalokCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double panjang = Double.parseDouble(etBalokPanjang.getText().toString());
                    double lebar = Double.parseDouble(etBalokLebar.getText().toString());
                    double tinggi = Double.parseDouble(etBalokTinggi.getText().toString());
                    double volume = panjang * lebar * tinggi;
                    tvBalokResult.setText("Volume of Cuboid: " + volume);
                } catch (NumberFormatException e) {
                    tvBalokResult.setText("Invalid input. Please enter a number.");
                }
            }
        });


        btnPiramidaCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                double sisi = Double.parseDouble(etPiramidaSisi.getText().toString());
                double tinggi = Double.parseDouble(etPiramidaTinggi.getText().toString());
                double volume = (sisi * sisi * tinggi) / 3;
                tvPiramidaResult.setText("Volume of Pyramids: " + volume);
            } catch (NumberFormatException e) {
                tvPiramidaResult.setText("Invalid input. Please enter a number.");
            }}
        });

        btnTabungCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                double jari = Double.parseDouble(etTabungJari.getText().toString());
                double tinggi = Double.parseDouble(etTabungTinggi.getText().toString());
                double volume = Math.PI * jari * jari * tinggi;
                tvTabungResult.setText("Volume of Cylinder: " + volume);
                } catch (NumberFormatException e) {
                    tvTabungResult.setText("Invalid input. Please enter a number.");
                }
            }
        });

        return view;
    }
}
