package com.example.whattoeattoday.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.whattoeattoday.R
import kotlinx.android.synthetic.main.gridview_item.view.*

// img_list와 text_list는 메인에서 텍스트와 이미지 각각을 하나씩 받아와서 그리드뷰에 넣어주는 작업
class GridviewAdapter(val context: Context, val img_list: TypedArray, val text_list: Array<String>) : BaseAdapter() {

    // 그리드뷰 어댑터 만들기.
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.gridview_item, null)

        view.gridview_text.text = text_list[position]
        view.gridview_img.setImageResource(img_list.getResourceId(position, -1))
        return view
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return img_list.length()
    }

}