package com.example.mathics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class SecondFragment extends Fragment {
    private EditText etLength, etWidth, etBase, etHeight, etRadius;
    private Button btnSquareCalculate, btnCircleCalculate, btnTriangleCalculate;
    private TextView tvSquareResult, tvCircleResult, tvTriangleResult;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        etLength = view.findViewById(R.id.et_square_length);
        etWidth = view.findViewById(R.id.et_square_width);
        etBase = view.findViewById(R.id.et_triangle_base);
        etHeight = view.findViewById(R.id.et_triangle_height);
        etRadius = view.findViewById(R.id.et_circle_radius);
        btnSquareCalculate = view.findViewById(R.id.btn_square_calculate);
        btnCircleCalculate = view.findViewById(R.id.btn_circle_calculate);
        btnTriangleCalculate = view.findViewById(R.id.btn_triangle_calculate);
        tvSquareResult = view.findViewById(R.id.tv_square_result);
        tvCircleResult = view.findViewById(R.id.tv_circle_result);
        tvTriangleResult = view.findViewById(R.id.tv_triangle_result);

        btnSquareCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calculate area of a square
                try {
                    double length = Double.parseDouble(etLength.getText().toString());
                    double width = Double.parseDouble(etWidth.getText().toString());
                    double area = length * width;
                    tvSquareResult.setText("Area of square: " + area);
                } catch (NumberFormatException e) {
                    tvSquareResult.setText("Please enter valid numbers");
                }
            }
        });

        btnCircleCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calculate area of a circle
                try {
                    double radius = Double.parseDouble(etRadius.getText().toString());
                    double area = Math.PI * radius * radius;
                    tvCircleResult.setText("Area of circle: " + area);
                } catch (NumberFormatException e) {
                    tvCircleResult.setText("Please enter a valid number");
                }
            }
        });

        btnTriangleCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calculate area of a triangle
                try {
                    double base = Double.parseDouble(etBase.getText().toString());
                    double height = Double.parseDouble(etHeight.getText().toString());
                    double area = 0.5 * base * height;
                    tvTriangleResult.setText("Area of triangle: " + area);
                } catch (NumberFormatException e) {
                    tvTriangleResult.setText("Please enter valid numbers");
                }
            }
        });


        return view;
    }
}
