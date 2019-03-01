package com.yichen.common.utils

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.yichen.common.injection.module.GlideApp

/**
 * Created by Chen on 2019/2/15
 */
object ImageLoader {

    fun loadUrlImage(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .into(imageView)
    }

    fun loadUrlCircleImage(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .apply(
                RequestOptions.bitmapTransform(
                    CircleCrop()
                )
            )
            .into(imageView)
    }

    fun loadUrlCircleImage(context: Context, id: Int, imageView: ImageView) {
        GlideApp.with(context)
            .load(id)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .apply(
                RequestOptions.bitmapTransform(
                    CircleCrop()
                )
            )
            .into(imageView)
    }

    fun loadUrlBlurImage(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .apply(
                RequestOptions.bitmapTransform(
                    GlideBlurformation(context, 0.5f, 20f)
                )
            )
            .into(imageView)
    }

    fun loadUrlBlurImage(context: Context, url: String, imageView: ImageView, scale: Float, radius: Float) {
        GlideApp.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .apply(
                RequestOptions.bitmapTransform(
                    GlideBlurformation(context, scale, radius)
                )
            )
            .into(imageView)
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun loadUrlImage(activity: Activity, url: String, imageView: ImageView) {
        if (activity.isDestroyed)
            return
        GlideApp.with(activity)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .into(imageView)
    }
}