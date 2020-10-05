package com.example.whattoeattoday.menu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.whattoeattoday.R
import com.example.whattoeattoday.adapter.*
import kotlinx.android.synthetic.main.activity_category.*
import org.jetbrains.anko.support.v4.*


class Category() : Fragment() {

    lateinit var input: InputMethodManager
    lateinit var editText:EditText
    lateinit var searchButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_category, container, false)

        setupSearchView(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // ViewPagerAdapter 연결 부분
        val adapter = ViewPagerAdapter(this.context!!.applicationContext)
        viewpager.adapter = adapter

        // GirdViewAdapter 연결 부분
        val img = resources.obtainTypedArray(R.array.category_img)
        val name = resources.getStringArray(R.array.category_name)

        val gridviewAdapter = GridViewAdapter(this.context?.applicationContext, img, name)
        gridview.adapter = gridviewAdapter

        gridview.setOnItemClickListener{ _, _, i, _ ->
            startActivity<CategoryItem>(
                "category" to i
            )
        }
    }

    private fun setupSearchView(view: View) {
        editText = view.findViewById(R.id.search_editText)
        searchButton = view.findViewById(R.id.btn_search)

        searchButton.setOnClickListener{
            println("================================================ 눌림")
            val searchResult = editText.text.toString()
            if(searchResult.isEmpty()) {
                toast("검색어를 입력해주세요.")
            }else {
                startActivity<SearchResult>(
                    "searchKeyword" to editText.text.toString()
                )
            }
        }

    }

}
