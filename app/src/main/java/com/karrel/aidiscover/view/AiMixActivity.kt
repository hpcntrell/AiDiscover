package com.karrel.aidiscover.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karrel.aidiscover.R
import com.karrel.aidiscover.ext.debouncedClicks
import com.karrel.aidiscover.ext.dp
import com.karrel.aidiscover.ext.onDestroy
import com.karrel.aidiscover.util.RVBLog
import com.karrel.aidiscover.view.epoxy.ai_recommend.AiRecommendController
import com.karrel.aidiscover.viewmodel.AiMixViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_ai_mix.*

class AiMixActivity : AppCompatActivity() {

    private val viewModel = AiMixViewModel()
    private val recommendLayoutManager by lazy {
        GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
    }

    private val recommendController by lazy {
        return@lazy AiRecommendController().apply {
            onItemClickListener = { index, item ->

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_mix)

        RVBLog.d("AiMixActivity > onCreate")

        setupButtonEvents()
        setupViewModel()
        loadWhoLikesMeList()
        setupRecommendList()
    }

    private fun setupButtonEvents() {
        btnCreateAi
            .debouncedClicks()
            .onDestroy(this)
            .subscribe {
                finish()
            }
    }

    private fun setupRecommendList() {
        ervWhoLooks.apply {
            layoutManager = recommendLayoutManager
            setController(recommendController)
            isNestedScrollingEnabled = false
        }
    }

    private fun setupViewModel() {
        viewModel.getMixedImage()
            .observeOn(AndroidSchedulers.mainThread())
            .onDestroy(this)
            .subscribe({ img ->
                sdvMixedProfile.setActualImageResource(img)
            }, { e ->
                e.printStackTrace()
            })


        viewModel.getCandidateImage()
            .observeOn(AndroidSchedulers.mainThread())
            .onDestroy(this)
            .subscribe({ list ->
                sdvCandidate1.setActualImageResource(list[0])
                sdvCandidate2.setActualImageResource(list[1])
                sdvCandidate3.setActualImageResource(list[2])
                sdvCandidate4.setActualImageResource(list[3])
                sdvCandidate5.setActualImageResource(list[4])
            }, {
                it.printStackTrace()
            })
    }

    private fun loadWhoLikesMeList() {
        onRequestRecommendList()

        viewModel.loadAiRecommendUserMeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onDestroy(this)
            .subscribe({ list ->
                println("AiMixActivity recommend list : $list")
                onLoadedRecommendList()

                recommendController.setData(list)
            }, { e ->
                e.printStackTrace()
            })
    }

    private fun onLoadedRecommendList() {
        clProgress.isVisible = false
        llRecommendList.isVisible = true
        btnCreateAi.isVisible = true
    }

    private fun onRequestRecommendList() {
        clProgress.isVisible = true
        llRecommendList.isVisible = false
        btnCreateAi.isVisible = false
    }
}
