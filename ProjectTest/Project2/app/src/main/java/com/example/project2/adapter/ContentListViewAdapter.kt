package com.example.project2.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.project2.ContentsListModel
import com.example.project2.R
import com.example.project2.URLtoBitmapTask
import java.net.URL


//listview 를 뿌려줄 어댑터

class ContentListViewAdapter(val context: Context, val list: List<ContentsListModel.Info>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.rest_listview_item, null)

            holder = ViewHolder()

            holder.view_image1 = view.findViewById(R.id.lv_image_area)
            holder.rest_name = view.findViewById(R.id.lv_textview_1)
            holder.rest_main_menu = view.findViewById(R.id.lv_textview_3)


            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        val item = list[position]

//        Glide.with().load("http://192.168.10.74:3000/img/myPhoto1594888267068.jpeg").into(holder.view_image1)
//        Glide.with(this).

        println("============================================================================================================================== ${item.restaurant_logo}")
        var image_task: URLtoBitmapTask = URLtoBitmapTask()
        image_task = URLtoBitmapTask().apply {
            url = URL("http://192.168.10.74:3000${item.restaurant_logo}")
        }
        var bitmap: Bitmap = image_task.execute().get()

        holder.view_image1?.setImageBitmap(bitmap)


        holder.rest_name?.text = item.restaurant_name
        holder.rest_main_menu?.text = item.restaurant_main_menu1 + ", " + item.restaurant_main_menu2

        return view
    }





    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

    private class ViewHolder{
        var rest_name: TextView? = null
        var rest_main_menu : TextView? = null
        var view_image1: ImageView? = null
    }

}