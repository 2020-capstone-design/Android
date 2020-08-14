package com.example.project2.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ContentsListModel
import com.example.project2.R
import com.example.project2.URLtoBitmapTask
import com.example.project2.bottomnavigation.category.RestInfoActivity
import org.jetbrains.anko.startActivity
import java.net.URL


//listview 를 뿌려줄 어댑터

class ContentListViewAdapter(val context: Context, val list: List<ContentsListModel.Info>): RecyclerView.Adapter<Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.rest_listview_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list)
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                context.startActivity<RestInfoActivity>(
                    "restNum" to list[position].restaurant_num,
                    "restName" to list[position].restaurant_name
                )
            }

        })

    }


}

class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    val restImage = itemView?.findViewById<ImageView>(R.id.lv_image_area)
    val restName = itemView?.findViewById<TextView>(R.id.lv_textview_1)
    val restMainMenu = itemView?.findViewById<TextView>(R.id.lv_textview_3)

    fun bind (list: List<ContentsListModel.Info>) {

        restName?.text = list[position].restaurant_name
        restMainMenu?.text = list[position].restaurant_main_menu1 + ", " + list[position].restaurant_main_menu2

        var image_task: URLtoBitmapTask = URLtoBitmapTask()
        image_task = URLtoBitmapTask().apply {
            url = URL("http://192.168.10.74:3000${list[position].restaurant_logo}")
        }
        var bitmap: Bitmap = image_task.execute().get()

        restImage?.setImageBitmap(bitmap)

    }

}