package com.karrel.aidiscover.viewmodel

import com.karrel.aidiscover.R
import io.reactivex.Single

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
}