package com.example.project2.bottomnavigation.category.fragment.restinfo

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.project2.ContentsListModel
import com.example.project2.R
import com.example.project2.Service
import kotlinx.android.synthetic.main.activity_korean_food.view.*
import kotlinx.android.synthetic.main.activity_rest_info.*
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MenuFragment(val restNum : String) : Fragment() {

    var menuList = ArrayList<ContentsListModel.Menu>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_menu, container, false)

        load(view)

//        menu_listview.setOnTouchListener { v, event ->
//            scroll_view.requestDisallowInterceptTouchEvent(true)
//            false
//        }


        return view
    }

    private fun load(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.10.74:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pref = PreferenceManager.getDefaultSharedPreferences(this.context)
        val universal = pref.getString("UniversalName", "")

        val server = retrofit.create(Service::class.java)



        if (universal != null) {
            server.testRequest2(restNum).enqueue(object : Callback<ContentsListModel> {
                override fun onFailure(call: Call<ContentsListModel>, t: Throwable) {
                    Log.d("에러 : ", t.toString())
                    Toast.makeText(context, "정보 받아오기 실패" + universal, Toast.LENGTH_LONG)
                        .show()
                }

                override fun onResponse(
                    call: Call<ContentsListModel>,
                    response: Response<ContentsListModel>
                ) {
                    Toast.makeText(context, "서버 연결 성공!" + universal, Toast.LENGTH_LONG)
                        .show()

                    var data = response.body()

                    val list_adapter = ListAdapter(requireContext(), data!!.menus)
                    view?.menu_listview.adapter = list_adapter

                    menuList = data!!.menus as ArrayList<ContentsListModel.Menu>
                }

            })
        }
    }

}