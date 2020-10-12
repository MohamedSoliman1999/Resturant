package com.example.sharedprefrence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sharedprefrence.api.myViewModel;
import com.example.sharedprefrence.classes.categories;
import com.example.sharedprefrence.classes.catogeryMeals;
import com.example.sharedprefrence.classes.mealDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements recAdapter.onItemClickListener{

    myViewModel vm;
    RecyclerView rec;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        rec = (RecyclerView)findViewById(R.id.rec_categories);
        pb = (ProgressBar)findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);
        vm=ViewModelProviders.of(this).get(myViewModel.class);
        vm.getCategories();
        vm.getMutableLiveData().observe(this, new Observer<categories>() {
            @Override
            public void onChanged(categories categories) {
                Log.e("###",categories.getCategories().get(0).getStrCategory());
                recAdapter adapter = new recAdapter();
                adapter.setCategs(categories.getCategories());
                adapter.setListener(MenuActivity.this);
                rec.setAdapter(adapter);
                rec.setLayoutManager(new GridLayoutManager(MenuActivity.this,3));
                rec.setHasFixedSize(true);
                pb.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onItemClick(List<categories.CategoriesBean>categs) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("category", (Serializable) categs);
        Intent intent=new Intent(MenuActivity.this,CategoryMealsActivity.class);
        intent.putExtra("category",bundle);
        startActivity(intent);
    }
}