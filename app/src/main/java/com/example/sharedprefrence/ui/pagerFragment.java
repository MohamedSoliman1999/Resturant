package com.example.sharedprefrence.ui;

import android.content.Context;
import android.os.Bundle;

import com.example.sharedprefrence.api.myViewModel;
import com.example.sharedprefrence.classes.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.sharedprefrence.R;


public class pagerFragment extends Fragment implements mealAdapter.onMealClickListener{

    RecyclerView mealsRec;
    mealAdapter mealAdapter;
    categories.CategoriesBean category;
    myViewModel vm;
    ProgressBar progressBar;
    public pagerFragment() {

    }

    public pagerFragment(categories.CategoriesBean category) {
        this.category = category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        mealsRec = view.findViewById(R.id.rec_meals);
        progressBar=(ProgressBar) view.findViewById(R.id.pb);
        progressBar.setVisibility(View.VISIBLE);
        vm = ViewModelProviders.of(this).get(myViewModel.class);
        vm.getCategoryMeals(category.getStrCategory());
        mealAdapter=new mealAdapter();
        mealAdapter.setListener(this);
        vm.getCategoryLiveData().observe(getViewLifecycleOwner(), new Observer<catogeryMeals>() {
            @Override
            public void onChanged(catogeryMeals catogeryMeals) {
                mealAdapter.setMeals(catogeryMeals.getMeals());
                mealsRec.setAdapter(mealAdapter);
                mealsRec.setLayoutManager(new GridLayoutManager(getContext(),2));
                mealsRec.setHasFixedSize(true);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }

    @Override
    public void onMealClick(String id) {

    }
}