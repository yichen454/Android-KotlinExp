package com.yichen.summer.adapter

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.yichen.common.recyclerview.adapter.HelperRecyclerViewAdapter
import com.yichen.common.recyclerview.adapter.HelperRecyclerViewHolder
import com.yichen.common.utils.FormatUtil
import com.yichen.common.utils.ImageLoader
import com.yichen.summer.R
import com.yichen.summer.entity.SummerInfoData

/**
 * Created by Chen on 2019/2/18
 */
class TypeAdapter(private val context: Context) :
    HelperRecyclerViewAdapter<SummerInfoData>(context, R.layout.summer_item_type) {

    interface InnerClickListener {
        fun onAvatarClick(itemData: SummerInfoData)
    }

    var innerClickListener: InnerClickListener? = null

    override fun HelperBindData(viewHolder: HelperRecyclerViewHolder?, position: Int, item: SummerInfoData?) {
        bindImgItem(viewHolder!!, item!!)
    }

    private fun bindImgItem(holder: HelperRecyclerViewHolder, itemData: SummerInfoData) {
        val ivAvatar: ImageView = holder.getView(R.id.iv_avatar)
        val tvNick: TextView = holder.getView(R.id.tv_nick)
        val tvSchool: TextView = holder.getView(R.id.tv_school)
        val tvTime: TextView = holder.getView(R.id.tv_time)
        val tvContent: TextView = holder.getView(R.id.tv_content)
        val tvCommentCount: TextView = holder.getView(R.id.tv_comment_count)
        val tvLikeCount: TextView = holder.getView(R.id.tv_like_count)

        tvContent.text = itemData.content
        if (itemData.anonymous_user != null) {
            tvNick.text = itemData.anonymous_user!!.nickname
            ImageLoader.loadUrlCircleImage(context, itemData.anonymous_user!!.avatar, ivAvatar)
        } else {
            if (TextUtils.isEmpty(itemData.user.nickname) || TextUtils.isEmpty(itemData.user.avatar)) {
                tvNick.text = "匿名用户"
                ImageLoader.loadUrlCircleImage(context, R.drawable.anonymous, ivAvatar)
            } else {
                tvNick.text = itemData.user.nickname
                ImageLoader.loadUrlCircleImage(context, itemData.user.avatar, ivAvatar)
            }
        }
        tvSchool.text = itemData.user.school.name
        tvCommentCount.text = (itemData.answers_count + itemData.comments_count).toString()
        tvLikeCount.text = itemData.votes_count.toString()
        val pt: String = if (!TextUtils.isEmpty(itemData.published_at)) itemData.published_at else itemData.created_at
        tvTime.text = FormatUtil.formatDate(FormatUtil.parseTime(pt).time)
        if (innerClickListener != null) {
            ivAvatar.setOnClickListener {
                innerClickListener!!.onAvatarClick(itemData)
            }
        }
    }

}