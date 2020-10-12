package com.example.sharedprefrence.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.example.sharedprefrence.classes.*;

public interface api {
    @GET("categories.php")
    Call<categories> getAllCategories();

    @GET("lookup.php/")
    Call<mealDetail> getSpecificMeal(@Query("i") int id);

    @GET("filter.php/")
    Call<catogeryMeals>getCategoryMeals(@Query("c") String categoryName);
}
