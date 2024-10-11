package com.example.mathics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {
    TextView usernameView;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    PagerAdapter pagerAdapter;
    FirstFragment firstFragment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usernameView = findViewById(R.id.tv_username);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username_user");
        if (username != null) {
            usernameView.setText(username);
        }

        setViewPager2(viewPager2);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(pagerAdapter.getFragmentTitle(position))).attach();

        firstFragment = (FirstFragment) pagerAdapter.createFragment(0);
    }

    public void setViewPager2(ViewPager2 viewPager2) {
        if (pagerAdapter == null) {
            pagerAdapter = new PagerAdapter(this);
            pagerAdapter.addFragment(new FirstFragment(), "Counter");
            pagerAdapter.addFragment(new SecondFragment(), "Area");
            pagerAdapter.addFragment(new ThirdFragment(), "Volume");
            viewPager2.setAdapter(pagerAdapter);
        }
    }
}
