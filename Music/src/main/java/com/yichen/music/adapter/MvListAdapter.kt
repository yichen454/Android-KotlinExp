package com.yichen.music.adapter

import android.content.Context
import android.widget.TextView
import com.yichen.common.recyclerview.adapter.HelperRecyclerViewAdapter
import com.yichen.common.recyclerview.adapter.HelperRecyclerViewHolder
import com.yichen.common.utils.ImageLoader
import com.yichen.common.utils.ScreenUtils
import com.yichen.common.widgets.NiceImageView
import com.yichen.music.R
import com.yichen.music.entity.MusicMvListEntity

/**
 * Created by Chen on 2019/3/12
 */
class MvListAdapter(private var context: Context) :
    HelperRecyclerViewAdapter<MusicMvListEntity>(context, R.layout.music_item_mvlist) {

    private var imgHeight = (ScreenUtils.getScreenSize(context)[0] - ScreenUtils.dp2px(2) * 2) / 2

    override fun HelperBindData(viewHolder: HelperRecyclerViewHolder?, position: Int, item: MusicMvListEntity?) {
        val imageView: NiceImageView = viewHolder!!.getView(R.id.iv_cover)
        imageView.layoutParams.height = imgHeight
        ImageLoader.loadUrlImage(context, item!!.pic, imageView)
        viewHolder.getView<TextView>(R.id.tv_count).text = "${item.playCount}播放"
        viewHolder.getView<TextView>(R.id.tv_name).text = item.name
    }

}