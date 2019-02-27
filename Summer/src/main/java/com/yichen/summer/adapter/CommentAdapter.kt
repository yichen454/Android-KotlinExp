package com.yichen.summer.adapter

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.yichen.common.ui.adapter.BaseAdapter
import com.yichen.common.ui.adapter.BaseViewHolder
import com.yichen.common.utils.FormatUtil
import com.yichen.common.utils.ImageLoader
import com.yichen.summer.R
import com.yichen.summer.entity.SummerCommentData

/**
 * Created by Chen on 2019/2/21
 */
class CommentAdapter(private val context: Context) : BaseAdapter<SummerCommentData>(context) {

    interface InnerClickListener {
        fun onAvatarClick(itemData: SummerCommentData)
    }

    var innerClickListener: InnerClickListener? = null

    override fun getItemLayoutId(): Int {
        return R.layout.summer_item_comment
    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val itemData = mDatas[position]
        bindImgItem(holder, itemData)
    }

    private fun bindImgItem(holder: BaseViewHolder, itemData: SummerCommentData) {
        val ivAvatar: ImageView = holder.getView(R.id.iv_avatar)
        val tvNick: TextView = holder.getView(R.id.tv_nick)
        val tvContent: TextView = holder.getView(R.id.tv_content)
        val tvTime: TextView = holder.getView(R.id.tv_time)
        val tvLikeCount: TextView = holder.getView(R.id.tv_like_count)

        tvContent.text = itemData.content
        if (itemData.anonymous_user != null) {
            tvNick.text = itemData.anonymous_user.nickname
            ImageLoader.loadUrlCircleImage(context, itemData.anonymous_user.avatar, ivAvatar)
        } else {
            if (TextUtils.isEmpty(itemData.user.nickname) || TextUtils.isEmpty(itemData.user.avatar)) {
                tvNick.text = "匿名用户"
                ImageLoader.loadUrlCircleImage(context, R.drawable.anonymous, ivAvatar)
            } else {
                tvNick.text = itemData.user.nickname
                ImageLoader.loadUrlCircleImage(context, itemData.user.avatar, ivAvatar)
            }
        }
        tvTime.text = FormatUtil.formatDate(FormatUtil.parseTime(itemData.created_at).time)
        if (innerClickListener != null) {
            ivAvatar.setOnClickListener {
                innerClickListener!!.onAvatarClick(itemData)
            }
        }
        tvLikeCount.text = itemData.votes_count.toString()
    }

}