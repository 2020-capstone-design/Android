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
import com.example.whattoeattoday.vo.ServerAccess
import com.facebook.shimmer.ShimmerFrameLayout
import java.net.URL

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image = itemView.findViewById<ImageView>(R.id.restaurant_logo)
    private val bestIcon = itemView.findViewById<ImageView>(R.id.restaurant_best_image)
    private val text1 = itemView.findViewById<TextView>(R.id.restaurant_name)
    private val text2 = itemView.findViewById<TextView>(R.id.menu_intro)
    private val text3 = itemView.findViewById<TextView>(R.id.restaurant_best_menu)
    private val restStatus = itemView.findViewById<LinearLayout>(R.id.list_view_block)

    fun infoBind(list: List<ContentsListModel.Info>) {
        text1.text = list[adapterPosition].restaurant_name
        text3.text = list[adapterPosition].restaurant_main_menu

        if (!list[adapterPosition].restaurant_isOpen) {
            restStatus.setBackgroundColor(Color.LTGRAY)
        }

        val logo = list[adapterPosition].restaurant_logo

        val urlToBitmapTask = URLtoBitmapTask()

        urlToBitmapTask.imageBitmap(logo, image, true)
    }

    fun menuBind(list: List<ContentsListModel.Menu>) {
        text1.text = list[adapterPosition].menu_name
        text2.text = list[adapterPosition].menu_intro
        text3.text = list[adapterPosition].menu_price
        bestIcon.visibility = View.GONE
    }

}