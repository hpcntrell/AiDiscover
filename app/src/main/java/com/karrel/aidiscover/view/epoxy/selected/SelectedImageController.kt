package com.karrel.aidiscover.view.epoxy.selected

import com.airbnb.epoxy.TypedEpoxyController
import com.karrel.aidiscover.R
import com.karrel.aidiscover.view.item.DiscoverRecommendItem

class SelectedImageController : TypedEpoxyController<List<DiscoverRecommendItem>>() {


    companion object {
        private const val SIZE = 5
    }


    override fun buildModels(data: List<DiscoverRecommendItem>?) {

        println("SelectedImageController > buildModels > $data")

        for (i in 0 until SIZE) {
            selectedProfileViewHolder {
                id(i)

                data?.getOrNull(i)?.let {
                    println("SelectedImageController > image(it.resId)")
                    image(it.resId)
                } ?: image(R.drawable.img_aid_circle_ph)
            }

            if (i < SIZE - 1) {
                plusViewHolder {
                    id(i + SIZE)
                }
            }
        }
    }
}