package com.example.whattoeattoday.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.whattoeattoday.R
import com.example.whattoeattoday.vo.ContentsListModel
import kotlin.collections.HashMap

class ExpandableAdapter internal constructor(
    private val context: Context,
    private val titleList: Array<String>,
    private val dataList: HashMap<String, MutableList<ContentsListModel.Menu>>
) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        println("here ${this.dataList[this.titleList[listPosition]]!![expandedListPosition]}")

        return this.dataList[this.titleList[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as ContentsListModel.Menu


        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.expandable_child, null)
        }
        val menuName = convertView!!.findViewById<TextView>(R.id.menu_name)
        val menuIntro = convertView.findViewById<TextView>(R.id.menu_intro)
        val menuPrice = convertView.findViewById<TextView>(R.id.menu_price)
        menuName.text = expandedListText.menu_name
        menuIntro.text = expandedListText.menu_intro
        menuPrice.text = expandedListText.menu_price
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return this.dataList[this.titleList[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        return this.titleList[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.titleList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val listTitle = getGroup(listPosition) as String
        var convertView = convertView
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.expandable_parent, null)
        }
        val listTitleTextView: TextView = convertView!!.findViewById(R.id.menu_category)
        listTitleTextView.text = listTitle
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

}