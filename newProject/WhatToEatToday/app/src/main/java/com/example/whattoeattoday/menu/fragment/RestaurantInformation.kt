package com.example.whattoeattoday.menu.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.adapter.URLtoBitmapTask
import com.example.whattoeattoday.vo.ContentsListModel
import com.example.whattoeattoday.vo.SearchRetrofit
import retrofit2.Call
import retrofit2.Response
import java.net.URL

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
                    .setText(data.restaurant.restaurant_intro)


                println("here ============ ${data.restaurant.restaurant_outside_image} ${data.restaurant.restaurant_menu_image1} ${data.restaurant.restaurant_menu_image2}")

                val restaurantImage = data.restaurant.restaurant_outside_image
                val restaurantMenu1 = data.restaurant.restaurant_menu_image1
                val restaurantMenu2 = data.restaurant.restaurant_menu_image2

                println("here")

//                val restaurantImage:String? = data.restaurant.restaurant_outside_image
//                val restaurantMenu1:String? = data.restaurant.restaurant_menu_image1
//                val restaurantMenu2: String? = data.restaurant.restaurant_menu_image2

                val imageView1 = view.findViewById<ImageView>(R.id.restaurant_image)
                val imageView2 = view.findViewById<ImageView>(R.id.restaurant_menu_image1)
                val imageView3 = view.findViewById<ImageView>(R.id.restaurant_menu_image2)



                if(restaurantImage != "noImage") {
                    val image_task = URLtoBitmapTask().apply {
                        url = URL("http://192.168.10.68:3000${restaurantImage}")
                    }
                    val bitmap: Bitmap = image_task.execute().get()

                    imageView1.setImageBitmap(bitmap)
                } else {
                    imageView1.visibility = View.GONE
                }


                if(restaurantMenu1 != "noImage") {
                    val image_task = URLtoBitmapTask().apply {
                        url = URL("http://192.168.10.68:3000${restaurantMenu1}")
                    }
                    val bitmap: Bitmap = image_task.execute().get()

                    imageView2.setImageBitmap(bitmap)
                } else {
                    imageView2.visibility = View.GONE
                }


                if(restaurantMenu2 != "noImage") {
                    val image_task = URLtoBitmapTask().apply {
                        url = URL("http://192.168.10.68:3000${restaurantMenu2}")
                    }
                    val bitmap: Bitmap = image_task.execute().get()

                    imageView3.setImageBitmap(bitmap)
                } else {
                    imageView3.visibility = View.GONE
                }

            }

        })

        return view
    }

}