package com.example.whattoeattoday.vo

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

data class ContentsListModel (
    var restaurants: List<Info>,
    var restaurant: Info,
    var menus: List<Menu>,
    var result: List<Info>
){
    data class Info(
        var restaurant_name: String,
        var restaurant_category: String,
        var restaurant_university: String,
        var restaurant_main_menu: String,
        var restaurant_num: String,
        var restaurant_logo: String,
        var restaurant_isOpen: Boolean,

        var restaurant_phone: String,
        val restaurant_loc: String,
        val restaurant_intro: String,
        val restaurant_operating_time: String,
        val restaurant_closed_days: String,
        val restaurant_food_origin: String,
        var restaurant_outside_image: String,
        var restaurant_menu_image1: String,
        var restaurant_menu_image2: String
    )

    data class Menu(
        var menu_name: String,
        var menu_price: String,
        var menu_intro: String,
        var menu_category:String
    )
}

interface Service{

    @GET("/restaurant/list_recommended_restaurants/{restaurant_university}&{hashtag}")
    fun requestRecommendList(@Path("restaurant_university") restaurant_university: String,
                             @Path("hashtag") hashtag: String): Call<ContentsListModel>

    @GET("/restaurant/list_restaurants/{restaurant_university}&{restaurant_category}")
    fun requestCategoryList(@Path("restaurant_university") restaurant_university: String,
                            @Path("restaurant_category") restaurant_category: String): Call<ContentsListModel>

    @GET("/menu/list_menus/{restaurant_num}")
    fun requestMenuList(@Path("restaurant_num") restaurant_num: String): Call<ContentsListModel>

    @GET("/restaurant/list_restaurant/{restaurant_num}")
    fun requestInfoList(@Path("restaurant_num") restaurant_num: String): Call<ContentsListModel>

}

object SearchRetrofit {
    // 위에서 만든 Service를 연결해줍니다.
    fun getService(): Service = retrofit.create(Service::class.java)

    private val retrofit = Retrofit.Builder()
        .baseUrl(ServerAccess.URL())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}

object ServerAccess {
    fun URL() = "https://api.todaymenu.tk"
}
