package com.yichen.music.adapter

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.yichen.common.recyclerview.adapter.HelperRecyclerViewHolder
import com.yichen.common.recyclerview.group.GroupedRecyclerViewAdapter
import com.yichen.common.utils.ImageLoader
import com.yichen.common.utils.ScreenUtils
import com.yichen.common.widgets.NiceImageView
import com.yichen.music.R
import com.yichen.music.entity.MusicGroupBean
import com.yichen.music.entity.MusicSongListEntity

/**
 * Created by Chen on 2019/3/4
 */
class SongListAdapter(private var context: Context) :
    GroupedRecyclerViewAdapter<MusicGroupBean<String, MusicSongListEntity>>(context) {

    private var imgHeight = (ScreenUtils.getScreenSize(context)[0] - ScreenUtils.dp2px(2) * 4) / 3

    override fun getGroupCount(): Int {
        return groups.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return getGroup(groupPosition).childern.size
    }

    override fun hasHeader(groupPosition: Int): Boolean {
        return !TextUtils.isEmpty(getGroup(groupPosition).title)
    }

    override fun hasFooter(groupPosition: Int): Boolean {
        return false
    }

    override fun getHeaderLayout(viewType: Int): Int {
        return R.layout.music_header_sticky_songlist
    }

    override fun getFooterLayout(viewType: Int): Int {
        return 0
    }

    override fun getChildLayout(viewType: Int): Int {
        return R.layout.music_item_songlist
    }

    override fun onBindHeaderViewHolder(
        holder: HelperRecyclerViewHolder?,
        groupPosition: Int,
        item: MusicGroupBean<String, MusicSongListEntity>?
    ) {
        holder!!.setText(R.id.tv_title, item!!.title)
    }

    override fun onBindFooterViewHolder(
        holder: HelperRecyclerViewHolder?,
        groupPosition: Int,
        item: MusicGroupBean<String, MusicSongListEntity>?
    ) {
    }

    override fun onBindChildViewHolder(
        holder: HelperRecyclerViewHolder?,
        groupPosition: Int,
        childPosition: Int,
        item: MusicGroupBean<String, MusicSongListEntity>?
    ) {
        holder!!.setText(R.id.tv_title, item!!.childern[childPosition].title)
        val imageView: NiceImageView = holder.getView(R.id.iv_cover)
        imageView.layoutParams.height = imgHeight
        ImageLoader.loadUrlImage(
            context,
            item.childern[childPosition].coverImgUrl,
            imageView
        )
    }
}