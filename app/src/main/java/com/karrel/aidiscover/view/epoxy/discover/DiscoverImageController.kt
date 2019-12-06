package com.karrel.aidiscover.view.epoxy.discover

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.karrel.aidiscover.ext.dp
import com.karrel.aidiscover.view.item.DiscoverRecommendItem

class DiscoverImageController(private val context: Context) :
    AsyncEpoxyController() {

    var onItemClickListener: ((index: Int, item: DiscoverRecommendItem) -> Unit)? = null

    private val windowWidth: Int
        get() = run {
            val metrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay.getMetrics(metrics)
            return metrics.widthPixels
        }

    private var data: List<DiscoverRecommendItem>? = null

    fun setData(data: List<DiscoverRecommendItem>?){
        this.data = data
    }

    override fun buildModels() {

        lottieViewHolder {
            id(Int.MIN_VALUE)
            spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }

            val width = windowWidth - 16.dp // window width - margin left
            width(width)
        }

        data?.forEachIndexed { index, item ->
            discoverViewHolder {
                id(index)
                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount / 2 }
                val width = windowWidth / 2 - 16.dp // window width - margin left
                width(width)
                image(item.resId)
                name(item.name)
                onClickListener {
                    onItemClickListener?.invoke(index, data!![index])
                }
            }
        }

        discoverFooterViewHolder {
            id(Int.MAX_VALUE)
            spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
        }
    }
}