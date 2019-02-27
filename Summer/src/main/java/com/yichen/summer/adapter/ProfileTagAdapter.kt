package com.yichen.summer.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yichen.summer.R
import com.yichen.summer.entity.SummerTagEntity
import com.yichen.summer.widgets.tagcloud.TagsAdapter

/**
 * Created by Chen on 2019/2/25
 */
class ProfileTagAdapter(var list: List<SummerTagEntity>) : TagsAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getView(context: Context?, position: Int, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.summer_item_tag_cloud, parent, false)
        val tv: TextView = view.findViewById<TextView>(R.id.tag)
        tv.text = list[position].tag
        (tv.background as GradientDrawable).setColor(context!!.resources.getColor(list[position].colorId))
        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getPopularity(position: Int): Int {
        return position % 5
    }

    override fun onThemeColorChanged(view: View?, themeColor: Int, alpha: Float) {
    }

}