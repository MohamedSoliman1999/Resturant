package com.example.sharedprefrence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.sharedprefrence.classes.categories;
import com.example.sharedprefrence.ui.pagerAdapter;
import com.example.sharedprefrence.ui.pagerFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoryMealsActivity extends AppCompatActivity {

    ViewPager vp;
    TabLayout tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_meals);
        tab = (TabLayout)findViewById(R.id.tab);
        vp = (ViewPager)findViewById(R.id.vp);

        Bundle bundle = getIntent().getBundleExtra("category");
        List<categories.CategoriesBean> categs = (List<categories.CategoriesBean>)bundle.getSerializable("category");

        pagerAdapter adapter = new pagerAdapter(getSupportFragmentManager());
        for(int i=0;i<categs.size();i++){
            adapter.addFragment(new pagerFragment(categs.get(i)),categs.get(i).getStrCategory());
        }

        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }
}