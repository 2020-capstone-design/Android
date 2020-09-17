package com.example.whattoeattoday.menu.fragment.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.vo.ContentsListModel
import com.example.whattoeattoday.vo.SearchRetrofit
import com.example.whattoeattoday.vo.SharedPreference
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KoreanFood : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_korean_food, container, false)

        recyclerView = view.findViewById(R.id.listview_korean_food)

        SearchRetrofit.getService().requestCategoryList(SharedPreference.universalName, "한식").enqueue(object : Callback<ContentsListModel> {
            override fun onFailure(call: Call<ContentsListModel>, t: Throwable) {
                Log.d("에러 : ", t.toString())
                toast("정보 받아오기 실패")
            }

            override fun onResponse(
                call: Call<ContentsListModel>,
                response: Response<ContentsListModel>
            ) {
                val data = response.body()

            }

        })

        return view
    }

}