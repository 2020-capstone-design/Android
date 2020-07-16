package com.example.project2.bottomnavigation.category.fragment

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.project2.ContentsListModel
import com.example.project2.R
import com.example.project2.Service
import com.example.project2.adapter.ContentListViewAdapter
import kotlinx.android.synthetic.main.activity_snack_bar.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SnackBar : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.activity_snack_bar, container, false)



//        val list_array = arrayListOf<ContentsListModel>(
//            ContentsListModel("a", "b", "d"),
//            ContentsListModel("a", "b", "d"),
//            ContentsListModel("a", "b", "d"),
//            ContentsListModel("a", "b", "d"),
//            ContentsListModel("a", "b", "d")
//        )

        load()

        return view
    }

    fun load() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.10.74:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pref = PreferenceManager.getDefaultSharedPreferences(this.context)
        val universal = pref.getString("UniversalName", "")

        val server = retrofit.create(Service::class.java)

        if (universal != null) {
            server.testRequest(universal, "분식").enqueue(object : Callback<ContentsListModel> {
                override fun onFailure(call: Call<ContentsListModel>, t: Throwable) {
                    Log.d("에러 : ", t.toString())
                    Toast.makeText(context, "정보 받아오기 실패" + universal, Toast.LENGTH_LONG)
                        .show()
                }

                override fun onResponse(call: Call<ContentsListModel>, response: Response<ContentsListModel>) {
                    Toast.makeText(context, "서버 연결 성공!" + universal, Toast.LENGTH_LONG)
                        .show()

                    var data = response.body()

                    val list_adapter = ContentListViewAdapter(requireContext(), data!!.restaurants)
                    view?.listview_snackbar?.adapter = list_adapter

                }

            })
        }


    }
}