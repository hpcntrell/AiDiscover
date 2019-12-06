package com.karrel.aidiscover.viewmodel

import com.karrel.aidiscover.R
import com.karrel.aidiscover.base.BaseViewModel
import com.karrel.aidiscover.util.RVBLog
import com.karrel.aidiscover.view.item.DiscoverRecommendItem
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class AiDiscoverViewModel : BaseViewModel() {

    companion object {
        private const val MAX_SELECTED_ITEM = 5
    }

    val observeStartMixItem = PublishSubject.create<Unit>()

    val observeSelectedItem = BehaviorSubject.create<List<DiscoverRecommendItem>>()

    val observeCandidatorItem = BehaviorSubject.create<List<DiscoverRecommendItem>>()

    private val selectedDiscovers = ArrayDeque<DiscoverRecommendItem>()

    private var candidatorItem: ArrayList<DiscoverRecommendItem> = arrayListOf()

    val observeMixComplete = PublishSubject.create<Unit>()

    /**
     * 합성할 대상자를 로드한다.
     */
    fun loadMixCandidater() {
        val list = arrayListOf(
            DiscoverRecommendItem(R.drawable.woman1, "Annbel, 20"),
            DiscoverRecommendItem(R.drawable.woman2, "Annbel, 20"),
            DiscoverRecommendItem(R.drawable.woman3, "Annbel, 20"),
            DiscoverRecommendItem(R.drawable.woman4, "Annbel, 20"),
            DiscoverRecommendItem(R.drawable.woman5, "Annbel, 20"),
            DiscoverRecommendItem(R.drawable.woman6, "Annbel, 20"),
            DiscoverRecommendItem(R.drawable.woman7, "Annbel, 20"),
            DiscoverRecommendItem(R.drawable.woman8, "Annbel, 20")
        )

        candidatorItem = list

        observeCandidatorItem.onNext(candidatorItem)
    }

    fun selectDiscoverItem(item: DiscoverRecommendItem) {
        RVBLog.d("AiDiscoverViewModel > selectDiscoverItem > item : $item")

        selectedDiscovers.add(item)

        removeCandidateItem(item)

        observeSelectedItem.onNext(selectedDiscovers.toList())

        val isFull = selectedDiscovers.size == MAX_SELECTED_ITEM
        if (isFull) {
            requestMixSelectedDiscover()
        }
    }

    private fun removeCandidateItem(item: DiscoverRecommendItem) {
        candidatorItem.remove(item)
        observeCandidatorItem.onNext(candidatorItem)
    }

    private fun requestMixSelectedDiscover() {
        onStartMixItems()

        Observable.timer(2, TimeUnit.SECONDS)
            .subscribe {
                observeMixComplete.onNext(Unit)
            }.disposeOnClear()
    }

    private fun onStartMixItems() {
        observeStartMixItem.onNext(Unit)
    }

    fun clearSelecteItem() {
        selectedDiscovers.clear()
        observeSelectedItem.onNext(selectedDiscovers.toList())
    }
}