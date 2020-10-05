package com.example.whattoeattoday.adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.menu.fragment.CategoryRestaurantInformation
import com.example.whattoeattoday.menu.fragment.Restaurant
import com.example.whattoeattoday.vo.ContentsListModel
import com.facebook.shimmer.ShimmerFrameLayout
import io.supercharge.shimmerlayout.ShimmerLayout
import org.jetbrains.anko.startActivity
import java.net.URL

class RestaurantInfoRecyclerViewAdapter(val context: Context, val list: List<ContentsListModel.Info>, val startActivity: String) :
    RecyclerView.Adapter<Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.rest_listview_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.infoBind(list)
        holder.itemView.setOnClickListener {
            val intent = when(startActivity) {
                "Restaurant" -> Intent(context, Restaurant::class.java)
                "CategoryRestaurantInformation" -> Intent(context, CategoryRestaurantInformation::class.java)
                else -> Intent(context, CategoryRestaurantInformation::class.java)
            }
            Intent(context, Restaurant::class.java)
            intent.putExtra("restNum", list[position].restaurant_num)
            intent.putExtra("restName", list[position].restaurant_name)
            intent.putExtra("restPhoneNumber", list[position].restaurant_phone)
            context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK))

        }
    }

}

