package com.yichen.gank.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.yichen.common.ui.adapter.BaseAdapter
import com.yichen.common.ui.adapter.BaseViewHolder
import com.yichen.common.utils.ImageLoader
import com.yichen.gank.R
import com.yichen.gank.entity.GankItemData

/**
 * Created by Chen on 2019/2/15
 */
class CategoryAdapter(private var context: Context) : BaseAdapter<GankItemData>(context) {

    override fun getItemLayoutId(): Int {
        return R.layout.gank_item_category
    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val itemData = mDatas[position]
        bindImgItem(holder, itemData)
    }

    private fun bindImgItem(holder: BaseViewHolder, itemData: GankItemData) {
        val tvDesc: TextView = holder.getView(R.id.tv_desc)
        val iv: ImageView = holder.getView(R.id.iv)
        val content: String = itemData.desc + "\n\n @ " + itemData.who
        tvDesc.text = content
        if (itemData.images != null && itemData.images.isNotEmpty()) {
            iv.visibility = View.VISIBLE
            ImageLoader.loadUrlImage(context, itemData.images[0], iv)
        } else {
            iv.visibility = View.GONE
        }
    }
}