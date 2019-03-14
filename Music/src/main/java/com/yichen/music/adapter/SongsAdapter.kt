package com.yichen.music.adapter

import android.content.Context
import android.widget.TextView
import com.yichen.common.recyclerview.adapter.HelperRecyclerViewAdapter
import com.yichen.common.recyclerview.adapter.HelperRecyclerViewHolder
import com.yichen.music.R
import com.yichen.music.entity.MusicSongListDetailEntity

/**
 * Created by Chen on 2019/3/14
 */
class SongsAdapter(context: Context) :
    HelperRecyclerViewAdapter<MusicSongListDetailEntity.SongEntity>(context, R.layout.music_item_songs) {


    override fun HelperBindData(
        viewHolder: HelperRecyclerViewHolder?,
        position: Int,
        item: MusicSongListDetailEntity.SongEntity?
    ) {
        viewHolder!!.getView<TextView>(R.id.tv_item_num).text = (position + 1).toString()
        viewHolder.getView<TextView>(R.id.tv_item_name).text = item!!.name
        viewHolder.getView<TextView>(R.id.tv_item_autor).text = item.singer
    }
}