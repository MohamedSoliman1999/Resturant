package com.example.sharedprefrence.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharedprefrence.R;
import com.example.sharedprefrence.classes.*;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class mealAdapter extends RecyclerView.Adapter<mealAdapter.mealViewHolder> {
    private  List<catogeryMeals.MealsBean> meals;
    private onMealClickListener listener;
    public List<catogeryMeals.MealsBean> getMeals() {
        return meals;
    }
    public void setMeals(List<catogeryMeals.MealsBean> meals) {
        this.meals = meals;
    }
    Context context;


    @NonNull
    @Override
    public mealAdapter.mealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new mealViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull mealAdapter.mealViewHolder holder, int position) {
        Glide.with(context).load(meals.get(position).getStrMealThumb()).into(holder.mealImg);
        holder.mealName.setText(meals.get(position).getStrMeal());
    }

    @Override
    public int getItemCount() {
        if(meals==null)return 0;
        return meals.size();
    }

    interface onMealClickListener{
        void onMealClick(String id);
    }

    public onMealClickListener getListener() {
        return listener;
    }

    public void setListener(onMealClickListener listener) {
        this.listener = listener;
    }

    public class mealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView heart,mealImg;
        TextView mealName;
        public mealViewHolder(@NonNull View itemView) {
            super(itemView);
            heart=(ImageView)itemView.findViewById(R.id.heart);
            mealImg=(ImageView)itemView.findViewById(R.id.mealImg);
            mealName=(TextView) itemView.findViewById(R.id.mealName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onMealClick(meals.get(getLayoutPosition()).getIdMeal());
        }
    }
}
