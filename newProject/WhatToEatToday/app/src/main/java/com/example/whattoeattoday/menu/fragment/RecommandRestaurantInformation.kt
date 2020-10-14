package com.example.whattoeattoday.menu.fragment

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.whattoeattoday.R
import com.example.whattoeattoday.adapter.URLtoBitmapTask
import com.example.whattoeattoday.vo.ContentsListModel
import com.example.whattoeattoday.vo.SearchRetrofit
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


class RecommandRestaurantInformation(val restNum: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_information, container, false)

        SearchRetrofit.getService().requestInfoList(restNum).enqueue(object :
            retrofit2.Callback<ContentsListModel> {
            override fun onFailure(call: Call<ContentsListModel>, t: Throwable) {
                Log.d("info error : ", t.toString())

            }

            override fun onResponse(
                call: Call<ContentsListModel>,
                response: Response<ContentsListModel>
            ) {
                val data = response.body()!!

                view.findViewById<TextView>(R.id.location).text = data.restaurant.restaurant_loc
                view.findViewById<TextView>(R.id.operating_time).text =
                    data.restaurant.restaurant_operating_time
                view.findViewById<TextView>(R.id.closed_day).text =
                    data.restaurant.restaurant_closed_days
                view.findViewById<TextView>(R.id.phone_number).text =
                    data.restaurant.restaurant_phone
                view.findViewById<TextView>(R.id.intro).text = data.restaurant.restaurant_intro

                val restaurantImage = data.restaurant.restaurant_outside_image
                val restaurantMenu1 = data.restaurant.restaurant_menu_image1
                val restaurantMenu2 = data.restaurant.restaurant_menu_image2


                val imageView1 = view.findViewById<ImageView>(R.id.restaurant_image)
                val imageView2 = view.findViewById<ImageView>(R.id.restaurant_menu_image1)
                val imageView3 = view.findViewById<ImageView>(R.id.restaurant_menu_image2)

                val urlToBitmapTask1 = URLtoBitmapTask()
                urlToBitmapTask1.imageBitmap(restaurantImage, imageView1)
                val urlToBitmapTask2 = URLtoBitmapTask()
                urlToBitmapTask2.imageBitmap(restaurantMenu1, imageView2)
                val urlToBitmapTask3 = URLtoBitmapTask()
                urlToBitmapTask3.imageBitmap(restaurantMenu2, imageView3)


                // 지오 코더

                val geocode = Geocoder(requireContext())
                val lat: Double
                val lon: Double
                var list: List<Address>? = null

                val str: String = data.restaurant.restaurant_loc
                try {
                    list = geocode.getFromLocationName(
                        str,  // 지역 이름
                        10
                    ) // 읽을 개수
                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생")
                }

                val mapView = MapView(requireActivity())

                if (list != null) {
                    if (list.isEmpty()) {
//                        R.id.tv.text = "해당되는 주소 정보는 없습니다"
                    } else {
                        val adder: Address = list[0]
                        lat = adder.latitude
                        lon = adder.longitude
                        println("위도: $lat 경도: $lon ========================")

                        // 지도 api
                        val mapViewContainer = view.findViewById(R.id.map_view) as ViewGroup
                        mapViewContainer.addView(mapView)

                        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lon), true)

                        val marker = MapPOIItem()
                        marker.itemName = "Default Marker"
                        marker.tag = 0
                        marker.mapPoint = MapPoint.mapPointWithGeoCoord(lat, lon)
                        marker.markerType = MapPOIItem.MarkerType.RedPin // 기본으로 제공하는 BluePin 마커 모양.
                        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

                        mapView.addPOIItem(marker)
                    }
                }


            }

        })

        return view
    }

}