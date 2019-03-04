package com.yichen.gank.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.yichen.common.recyclerview.adapter.HelperRecyclerViewAdapter
import com.yichen.common.recyclerview.adapter.HelperRecyclerViewHolder
import com.yichen.common.utils.ImageLoader
import com.yichen.gank.R
import com.yichen.gank.entity.GankItemData

/**
 * Created by Chen on 2019/3/4
 */
class CategoryAdapter(private var context: Context) :
    HelperRecyclerViewAdapter<GankItemData>(context, R.layout.gank_item_category) {

    override fun HelperBindData(viewHolder: HelperRecyclerViewHolder?, position: Int, item: GankItemData?) {
        val iv: ImageView = viewHolder!!.getView(R.id.iv)
        val content: String = item!!.desc + "\n\n @ " + item.who
        viewHolder.setText(R.id.tv_desc,content)
        if (item.images != null && item.images!!.isNotEmpty()) {
            iv.visibility = View.VISIBLE
            ImageLoader.loadUrlImage(context, item.images!![0], iv)
        } else {
            iv.visibility = View.GONE
        }
    }

}