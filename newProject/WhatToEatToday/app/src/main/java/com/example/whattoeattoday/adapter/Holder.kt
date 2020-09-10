package com.example.whattoeattoday.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.vo.ContentsListModel
import java.net.URL

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image = itemView.findViewById<ImageView>(R.id.restaurant_or_menu_image)
    private val bestIcon = itemView.findViewById<ImageView>(R.id.restarant_best_image)
    private val text1 = itemView.findViewById<TextView>(R.id.restaurant_or_menu_name)
    private val text2 = itemView.findViewById<TextView>(R.id.menu_intro)
    private val text3 = itemView.findViewById<TextView>(R.id.restaurant_best_menu_or_price)
    private val restStatus = itemView.findViewById<LinearLayout>(R.id.list_view_block)


    fun infoBind(list: List<ContentsListModel.Info>) {

        text1.text = list[adapterPosition].restaurant_name
        text3.text =
            list[adapterPosition].restaurant_main_menu1 + ", " + list[position].restaurant_main_menu2

        if (!list[adapterPosition].restaurant_on_off) {
            restStatus.setBackgroundColor(Color.LTGRAY)
        }

        val logo = list[adapterPosition].restaurant_logo

        if (logo != "noImage") {
            val image_task = URLtoBitmapTask().apply {
                url = URL("http://192.168.10.74:3000${logo}")
            }
            val bitmap: Bitmap = image_task.execute().get()

            image.setImageBitmap(bitmap)
        } else {
            image.setImageResource(R.drawable.default_restarant_logo)
        }

    }

    fun menuBind(list: List<ContentsListModel.Menu>) {
        text1.text = list[adapterPosition].menu_name
        text2.text = list[adapterPosition].menu_intro
        text3.text = list[adapterPosition].menu_price
        bestIcon.visibility = View.GONE
    }

}