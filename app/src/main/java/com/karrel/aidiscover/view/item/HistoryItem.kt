package com.karrel.aidiscover.view.item

import androidx.annotation.DrawableRes

data class HistoryItem(
    @DrawableRes val image:Int,
    val candidators: List<DiscoverRecommendItem>,
    val createTime: Long
)