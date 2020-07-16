package com.example.project2

import retrofit2.Call
import retrofit2.http.*

data class ContentsListModel (
    var restaurants: List<Info>,
    var menus: List<Menu>
){
    data class Info(
        var image: String,
        var rest_name: String,
        var rest_category: String,
        var rest_university: String,
        var rest_main_menu1: String,
        var rest_main_menu2: String,
        var rest_num: String
    )

    data class Menu(
        var menu_name: String,
        var menu_price: String
    )
}

interface Service{

    @FormUrlEncoded
    @POST("/v1/restaurant/list_rest")
    fun testRequest(@Field("rest_university") rest_university: String,
                    @Field("rest_category") rest_category: String): Call<ContentsListModel>

    @FormUrlEncoded
    @POST("v1/menu/list_menu")
    fun testRequest2(@Field("rest_num") rest_num: String): Call<ContentsListModel>

}


//{
//    data class Info (
////        var rest_logo
//    var rest_name: String,
//
//
//    // 가게 클릭시 필요한 정보
//
//    // 메뉴
//    var menu_name: String,
//    var menu_price: String,
//
//    // 가게 정보
//    var rest_phone: String,
//    var rest_loc: String,
//    val rest_intro: String,
//
//    // 서버로 보내줄 정보
//    var rest_university: String,
//    var rest_category: String
//
//    )
//}

