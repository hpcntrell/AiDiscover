package com.karrel.aidiscover.view.epoxy

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import com.airbnb.epoxy.TypedEpoxyController
import com.karrel.aidiscover.ext.dp

class DiscoverImageController(private val context: Context) : TypedEpoxyController<List<DiscoverRecommendItem>>() {

    private val windowWidth: Int
    get() = run {
        val metrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.widthPixels
    }


    override fun buildModels(data: List<DiscoverRecommendItem>?) {

        discoverViewHolder1 {
            id(0)
            spanSizeOverride { totalSpanCount, _, _ ->  totalSpanCount }

            val width = windowWidth - 16.dp // window width - margin left
            width(width)
        }

        val itemCnt = 8
        for( i in 1..itemCnt) {
            discoverViewHolder2 {
                id(i)
                spanSizeOverride { totalSpanCount, _, _ ->  totalSpanCount / 2}
                val width = windowWidth / 2 - 16.dp // window width - margin left
                width(width)
            }
        }

        discoverFooterViewHolder {
            id(90)
            spanSizeOverride { totalSpanCount, _, _ ->  totalSpanCount }
        }
    }
}