package com.example.whattoeattoday.menu.fragment

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.adapter.RestaurantRecyclerViewAdapter
import com.example.whattoeattoday.vo.ContentsListModel
import com.example.whattoeattoday.vo.SearchRetrofit
import com.example.whattoeattoday.vo.SharedPreference
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_recommend_result.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Response


class RecommendResult : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var imageView: ImageView
    lateinit var textView: TextView
    lateinit var shimmerLayout: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_result)


        recyclerView = findViewById(R.id.recommend_result_list)
        shimmerLayout = findViewById(R.id.shimmerFrameLayout)
        imageView = findViewById(R.id.empty_view)
        textView = findViewById(R.id.try_again)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()

        shimmerLayout.startShimmerAnimation()


        println("================= 1")

        recommend_result_list.layoutManager = LinearLayoutManager(this)

        SharedPreference.init(this)
        SearchRetrofit.getService().requestRecommendList(SharedPreference.universalName, "치킨").enqueue(object: retrofit2.Callback<ContentsListModel> {

            override fun onFailure(call: Call<ContentsListModel>, t: Throwable) {
                toast("실패 : $t")
                println("======== error $t")
                shimmerLayout.stopShimmerAnimation()
                shimmerLayout.visibility = GONE
                recyclerView.visibility = GONE

            }

            override fun onResponse(
                call: Call<ContentsListModel>,
                response: Response<ContentsListModel>
            ) {
                toast("서버 연결 성공 +${SharedPreference.universalName}")

                val data = response.body()

                if(response.code() == 200) {
                    println("200")

                    if(data != null) {
                        println("${data}")
                        shimmerLayout.stopShimmerAnimation()
                        shimmerLayout.visibility = GONE
                        if(data.result.isEmpty()) {
                            recyclerView.visibility = GONE
                            imageView.visibility = VISIBLE
                        } else {
                            recyclerView.adapter = RestaurantRecyclerViewAdapter(applicationContext, data.result)
                        }
                    }

                }

            }

        })

    }

}