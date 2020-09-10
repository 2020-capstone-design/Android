package com.example.whattoeattoday.menu.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.vo.ContentsListModel
import com.example.whattoeattoday.vo.SearchRetrofit
import io.supercharge.shimmerlayout.ShimmerLayout
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Response

class RestaurantMenu(val restNum: String) : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var imageView: ImageView
    lateinit var textView: TextView
    lateinit var shimmerLayout: ShimmerLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

//        recyclerView = view.findViewById(R.id.recommend_result_list)
//        imageView = view.findViewById(R.id.empty_view)
//        textView = view.findViewById(R.id.try_again)
//        shimmerLayout = view.findViewById(R.id.loading_animation)

//        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        SearchRetrofit.getService().requestMenuList(restNum).enqueue(object : retrofit2.Callback<ContentsListModel> {
            override fun onFailure(call: Call<ContentsListModel>, t: Throwable) {
                toast("실패 : $t")

            }

            override fun onResponse(
                call: Call<ContentsListModel>,
                response: Response<ContentsListModel>
            ) {
                val data = response.body()


                if(data!=null) {
                    println("======================== ${data.menus.size}")

                    var menuIntro: MutableSet<String> = mutableSetOf()
                    for(element in data.menus) {
                        menuIntro.add(element.menu_category)
                    }

                    println("${menuIntro}  0============================")


                    if(data.menus.isEmpty()) {

                        // 이 식당에 대한 메뉴가 없을 때
                        recyclerView.visibility = View.GONE
                        imageView.visibility = View.VISIBLE
                        textView.visibility = View.GONE
                        shimmerLayout.visibility = View.GONE

                    } else {

                        // 식당에 대한 메뉴 있을 떄
                        recyclerView.visibility = View.VISIBLE
                        imageView.visibility = View.GONE
                        textView.visibility = View.GONE
                        shimmerLayout.visibility = View.GONE
//                        recyclerView.adapter = MenuRecyclerViewAdapter(requireContext(), data.menus)
                    }

                } else {

                    // 로딩중일 떄
                    recyclerView.visibility = View.GONE
                    imageView.visibility = View.GONE
                    textView.visibility = View.GONE
                    shimmerLayout.startShimmerAnimation()

                }
            }

        })

        return view
    }


}