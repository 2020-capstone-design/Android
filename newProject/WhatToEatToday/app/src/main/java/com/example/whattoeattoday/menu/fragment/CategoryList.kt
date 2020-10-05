package com.example.whattoeattoday.menu.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.adapter.RestaurantInfoRecyclerViewAdapter
import com.example.whattoeattoday.vo.ContentsListModel
import com.example.whattoeattoday.vo.SearchRetrofit
import com.example.whattoeattoday.vo.SharedPreference
import com.facebook.shimmer.ShimmerFrameLayout
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryList(val num: String) : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var imageView: View
    lateinit var textView: View
    lateinit var shimmerLayout: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_korean_food, container, false)

        recyclerView = view.findViewById(R.id.category_restaurant_listview)
        shimmerLayout = view.findViewById(R.id.shimmerFrameLayout)
        imageView = view.findViewById(R.id.not_found)
        textView = view.findViewById(R.id.try_again)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.itemAnimator = DefaultItemAnimator()

        shimmerLayout.startShimmerAnimation()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        SearchRetrofit.getService().requestCategoryList(SharedPreference.universalName, num).enqueue(object : Callback<ContentsListModel> {
            override fun onFailure(call: Call<ContentsListModel>, t: Throwable) {
                Log.d("에러 : ", t.toString())
                toast("정보 받아오기 실패")

                shimmerLayout.stopShimmerAnimation()
                shimmerLayout.visibility = View.GONE
                recyclerView.visibility = View.GONE
                imageView.visibility = View.GONE
                textView.visibility = View.VISIBLE
            }

            override fun onResponse(
                call: Call<ContentsListModel>,
                response: Response<ContentsListModel>
            ) {
                val data = response.body()

                if(response.code() == 200) {
                    if(data != null) {
                        shimmerLayout.stopShimmerAnimation()
                        shimmerLayout.visibility = View.GONE
                        if(data.restaurants.isEmpty()) {
                            imageView.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        } else {
                            imageView.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE
                            recyclerView.adapter = RestaurantInfoRecyclerViewAdapter(requireContext(), data.restaurants, "CategoryRestaurantInformation")
                        }
                    }

                }
            }

        })

        return view
    }

}