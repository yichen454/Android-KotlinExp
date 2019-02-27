package com.yichen.common.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val views: SparseArray<View> = SparseArray()

    fun <T : View> getView(viewId: Int): T {
        var view = views.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }
}