package com.example.project2.bottomnavigation.category.fragment.restinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ContentsListModel
import com.example.project2.R
import org.jetbrains.anko.find
import org.w3c.dom.Text


// 메뉴 뿌려주는 리스트

class ListAdapter(
    val context: Context,
    val list: List<ContentsListModel.Menu>
) : RecyclerView.Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_listview_item, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list)
    }

}

class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    val menuName = itemView?.findViewById<TextView>(R.id.menu_textview_1)
    val menuPrice = itemView?.findViewById<TextView>(R.id.menu_textview_3)
    val menuImage = itemView?.findViewById<ImageView>(R.id.menu_image_area)

    fun bind(list: List<ContentsListModel.Menu>) {
        menuName?.text = list[position].menu_name
        menuPrice?.text = list[position].menu_price
    }

}


//override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//    val view: View
//    val holder: ViewHolder
//
//    if(convertView == null) {
//        view = LayoutInflater.from(context).inflate(R.layout.rest_listview_item, null)
//
//        holder = ViewHolder()
//
//        holder.view_image1 = view.findViewById(R.id.lv_image_area)
//        holder.view_text1 = view.findViewById(R.id.lv_textview_1)
//
//
//        view.tag = holder
//    } else {
//        holder = convertView.tag as ViewHolder
//        view = convertView
//    }
//
//    val item = list[position]
//
//    holder.view_text1?.text = item.rest_name
//
//    return view
//}
//
//override fun getItem(position: Int): Any {
//    return list.get(position)
//}
//
//override fun getItemId(position: Int): Long {
//    return 0
//}
//
//override fun getCount(): Int {
//    return list.size
//}
//
//private class ViewHolder{
//    var view_text1: TextView? = null
//    var view_image1: ImageView? = null
//}





/*

override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.menu_listview_item, null)

            holder = ViewHolder()

            holder.menuArea = view.findViewById(R.id.menu_textview_1)
            holder.menuPriceArea = view.findViewById(R.id.menu_textview_3)

            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        val item = list[position]

        holder.menuArea?.text = item.menu_name
        holder.menuPriceArea?.text = item.menu_price

        return view
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

    private class ViewHolder {
        var menuArea : TextView? = null
        var menuPriceArea : TextView? = null
    }

 */