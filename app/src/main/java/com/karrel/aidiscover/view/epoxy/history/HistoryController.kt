package com.karrel.aidiscover.view.epoxy.history

import com.airbnb.epoxy.TypedEpoxyController
import com.karrel.aidiscover.view.item.HistoryItem

class HistoryController : TypedEpoxyController<HistoryItem>() {


    var onItemDeleteListener: ((HistoryItem) -> Unit)? = null


    override fun buildModels(data: HistoryItem?) {

    }
}