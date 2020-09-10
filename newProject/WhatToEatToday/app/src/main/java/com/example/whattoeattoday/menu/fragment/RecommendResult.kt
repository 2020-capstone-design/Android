package com.example.whattoeattoday.menu.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.adapter.RestaurantRecyclerViewAdapter
import com.example.whattoeattoday.vo.ContentsListModel
import com.example.whattoeattoday.vo.SearchRetrofit
import com.example.whattoeattoday.vo.SharedPreference
import io.supercharge.shimmerlayout.ShimmerLayout
import kotlinx.android.synthetic.main.activity_recommend_result.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Response

class RecommendResult : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var imageView: ImageView
    lateinit var textView: TextView
    lateinit var shimmerLayout: ShimmerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_result)


        recyclerView = findViewById(R.id.recommend_result_list)
        imageView = findViewById(R.id.empty_view)
        textView = findViewById(R.id.try_again)
        shimmerLayout = findViewById(R.id.loading_animation)

        recommend_result_list.layoutManager = LinearLayoutManager(this)

        SearchRetrofit.getService().testRequest1(SharedPreference.universalName, "한식").enqueue(object: retrofit2.Callback<ContentsListModel> {
            override fun onFailure(call: Call<ContentsListModel>, t: Throwable) {
                toast("실패 : $t")

                // 네트워크 상태가 원활하지 않을 때
                recyclerView.visibility = GONE
                imageView.visibility = INVISIBLE
                textView.visibility = GONE
                shimmerLayout.visibility = GONE

            }

            override fun onResponse(
                call: Call<ContentsListModel>,
                response: Response<ContentsListModel>
            ) {
                toast("서버 연결 성공 +${SharedPreference.universalName}")

                val data = response.body()

                if(data!=null) {

                    if(data.restaurants.isEmpty()) {

                        // 이 음식에 대한 식당이 없을 때
                        recyclerView.visibility = GONE
                        imageView.visibility = VISIBLE
                        textView.visibility = GONE
                        shimmerLayout.visibility = GONE

                    } else {

                        // 음식에 대한 식당이 있을 떄
                        recyclerView.visibility = VISIBLE
                        imageView.visibility = GONE
                        textView.visibility = GONE
                        shimmerLayout.visibility = GONE
                        recyclerView.adapter = RestaurantRecyclerViewAdapter(applicationContext, data.restaurants)
                    }

                } else {

                    // 로딩중일 떄
                    recyclerView.visibility = GONE
                    imageView.visibility = GONE
                    textView.visibility = GONE
                    shimmerLayout.startShimmerAnimation()

                }
            }

        })

    }
}