package com.karrel.aidiscover.view.epoxy.ai_recommend

import com.airbnb.epoxy.TypedEpoxyController
import com.karrel.aidiscover.R
import com.karrel.aidiscover.util.RVBLog
import com.karrel.aidiscover.view.epoxy.discover.discoverViewHolder
import com.karrel.aidiscover.view.epoxy.selected.SelectedImageController
import com.karrel.aidiscover.view.epoxy.selected.plusViewHolder
import com.karrel.aidiscover.view.epoxy.selected.selectedProfileViewHolder
import com.karrel.aidiscover.view.item.DiscoverRecommendItem

class AiRecommendController : TypedEpoxyController<List<DiscoverRecommendItem>>() {
    var onItemClickListener: ((index: Int, item: DiscoverRecommendItem) -> Unit)? = null

    init {
        RVBLog.d("AiRecommendController > init")
    }

    override fun buildModels(data: List<DiscoverRecommendItem>?) {
        RVBLog.d("AiRecommendController > buildModels")

        data?.forEachIndexed { index, item ->
            RVBLog.d("AiRecommendController > buildModels > data : $item")

            aiRecommenedViewHolder {
                id(index)
                image(item.resId)
                name(item.name)
                onClickListener {
                    onItemClickListener?.invoke(index, item)
                }

                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount / 2 }
            }
        }
    }

}