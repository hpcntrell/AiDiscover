package com.karrel.aidiscover.viewmodel

import com.karrel.aidiscover.R
import com.karrel.aidiscover.view.item.DiscoverRecommendItem
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class AiMixViewModel {
    fun getMixedImage(): Single<Int> {


        return Single.just(R.drawable.woman1)
    }

    fun getCandidateImage(): Single<ArrayList<Int>> {
        val candidateImages = arrayListOf(
            R.drawable.woman1,
            R.drawable.woman2,
            R.drawable.woman3,
            R.drawable.woman4,
            R.drawable.woman5
        )
        return Single.just(candidateImages)
    }

    fun loadAiRecommendUserMeList(): Observable<ArrayList<DiscoverRecommendItem>> {
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

        return Observable.just(list).delay(1000, TimeUnit.MILLISECONDS)
    }
}