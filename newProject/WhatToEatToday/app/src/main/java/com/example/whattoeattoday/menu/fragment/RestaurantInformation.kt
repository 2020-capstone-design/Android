package com.example.whattoeattoday.menu.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.vo.ContentsListModel
import com.example.whattoeattoday.vo.SearchRetrofit
import retrofit2.Call
import retrofit2.Response

class RestaurantInformation(val restNum: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_information, container, false)

        SearchRetrofit.getService().requestInfoList(restNum).enqueue(object : retrofit2.Callback<ContentsListModel> {
            override fun onFailure(call: Call<ContentsListModel>, t: Throwable) {
                Log.d("info error : ", t.toString())

            }

            override fun onResponse(
                call: Call<ContentsListModel>,
                response: Response<ContentsListModel>
            ) {
                val data = response.body()!!

                view.findViewById<TextView>(R.id.location)
                    .setText(data.restaurant.restaurant_loc)
                view.findViewById<TextView>(R.id.operating_time)
                    .setText(data.restaurant.restaurant_operating_time)
                view.findViewById<TextView>(R.id.closed_day)
                    .setText(data.restaurant.restaurant_closed_days)
                view.findViewById<TextView>(R.id.phone_number)
                    .setText(data.restaurant.restaurant_phone)
                view.findViewById<TextView>(R.id.intro)
                    .setText(data.restaurant.restaurant_intro)            }

        })

        return view
    }

}