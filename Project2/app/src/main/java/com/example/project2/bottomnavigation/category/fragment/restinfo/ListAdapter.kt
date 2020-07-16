package com.example.project2.bottomnavigation.category.fragment.restinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.project2.ContentsListModel
import com.example.project2.R
import org.jetbrains.anko.find
import org.w3c.dom.Text

class ListAdapter(
    val context: Context,
    val list: List<ContentsListModel.Menu>
) : BaseAdapter() {
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