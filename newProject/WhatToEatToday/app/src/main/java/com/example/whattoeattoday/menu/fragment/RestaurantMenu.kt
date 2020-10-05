package com.example.whattoeattoday.menu.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.adapter.ExpandableAdapter
import com.example.whattoeattoday.vo.ContentsListModel
import com.example.whattoeattoday.vo.SearchRetrofit
import com.facebook.shimmer.ShimmerFrameLayout
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Response

class RestaurantMenu(val restNum: String) : Fragment() {

    lateinit var expandableListView: ExpandableListView
    lateinit var shimmerLayout: ShimmerFrameLayout
    lateinit var adapter: ExpandableListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        expandableListView = view.findViewById(R.id.expandable_list_view)
//        shimmerLayout = view.findViewById(R.id.shimmerFrameLayout)



        SearchRetrofit.getService().requestMenuList(restNum)
            .enqueue(object : retrofit2.Callback<ContentsListModel> {
                override fun onFailure(call: Call<ContentsListModel>, t: Throwable) {
                    toast("실패 : $t")
                }

                override fun onResponse(
                    call: Call<ContentsListModel>,
                    response: Response<ContentsListModel>
                ) {
                    val data = response.body()

                    if (response.code() == 200) {
                        println("200 22")
                        if(data!=null) {

                            val expandableListDetail = HashMap<String, MutableList<ContentsListModel.Menu>>()

                            val menuIntro: MutableSet<String> = mutableSetOf()
                            val mainMenu: MutableList<ContentsListModel.Menu> = ArrayList()
                            val setMenu: MutableList<ContentsListModel.Menu> = ArrayList()
                            val sideMenu: MutableList<ContentsListModel.Menu> = ArrayList()
                            val drink: MutableList<ContentsListModel.Menu> = ArrayList()

                            for (element in data.menus) {

                                println("========= $element")

                                menuIntro.add(element.menu_category)
                                when (element.menu_category){
                                    "메인메뉴" -> {
                                        mainMenu.add(element)
                                    }
                                    "세트메뉴" -> {
                                        setMenu.add(element)
                                    }
                                    "사이드메뉴" -> {
                                        sideMenu.add(element)
                                    }
                                    "음료" -> {
                                        drink.add(element)
                                    }
                                }
                            }

                            expandableListDetail["메인메뉴"] = mainMenu
                            expandableListDetail["세트메뉴"] = setMenu
                            expandableListDetail["사이드메뉴"] = sideMenu
                            expandableListDetail["음료"] = drink

                            var list: Array<String> = menuIntro.toTypedArray()

                            adapter = ExpandableAdapter(requireContext(), list, expandableListDetail)

                            expandableListView.setAdapter(adapter)



                        }
                    }

                }

            })

        return view
    }


}