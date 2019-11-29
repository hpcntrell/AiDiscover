package com.karrel.aidiscover.view.epoxy

import com.airbnb.epoxy.TypedEpoxyController

class DiscoverImageController : TypedEpoxyController<List<DiscoverRecommendItem>>() {
    override fun buildModels(data: List<DiscoverRecommendItem>?) {

        discoverViewHolder1 {
            id(0)
        }

        discoverViewHolder2 {
            id(1)
        }
        discoverViewHolder2 {
            id(2)
        }
        discoverViewHolder2 {
            id(3)
        }
        discoverViewHolder2 {
            id(4)
        }

    }
}