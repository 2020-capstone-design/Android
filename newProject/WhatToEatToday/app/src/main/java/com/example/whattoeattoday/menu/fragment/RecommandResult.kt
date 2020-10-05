package com.example.whattoeattoday.menu.fragment

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.adapter.RestaurantInfoRecyclerViewAdapter
import com.example.whattoeattoday.vo.ContentsListModel
import com.example.whattoeattoday.vo.SearchRetrofit
import com.example.whattoeattoday.vo.SharedPreference
import com.facebook.shimmer.ShimmerFrameLayout
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Response


class RecommandResult : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var imageView: View
    lateinit var textView: View
    lateinit var shimmerLayout: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_of_responses_to_requests)


        recyclerView = findViewById(R.id.recommend_result_list)
        shimmerLayout = findViewById(R.id.shimmerFrameLayout)
        imageView = findViewById(R.id.not_found)
        textView = findViewById(R.id.try_again)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()

        shimmerLayout.startShimmerAnimation()


        println("================= 1")

        recyclerView.layoutManager = LinearLayoutManager(this)

        SharedPreference.init(this)
        SearchRetrofit.getService().requestRecommendList(SharedPreference.universalName, "치킨").enqueue(object: retrofit2.Callback<ContentsListModel> {

            override fun onFailure(call: Call<ContentsListModel>, t: Throwable) {
                toast("실패 : $t")
                println("======== error $t")
                shimmerLayout.stopShimmerAnimation()
                shimmerLayout.visibility = GONE
                recyclerView.visibility = GONE
                textView.visibility = VISIBLE
                imageView.visibility = GONE

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
                        println("RecommandResult ${data}")
                        shimmerLayout.stopShimmerAnimation()
                        shimmerLayout.visibility = GONE
                        if(data.result.isEmpty()) {
                            println("here ===========")
                            imageView.visibility = VISIBLE
                            recyclerView.visibility = GONE
                        } else {
                            imageView.visibility = GONE
                            recyclerView.visibility = VISIBLE
                            recyclerView.adapter = RestaurantInfoRecyclerViewAdapter(applicationContext, data.result, "Restaurant")
                        }
                    }

                }

            }

        })

    }

}