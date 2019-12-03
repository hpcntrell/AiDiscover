package com.karrel.aidiscover.view

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.daimajia.easing.Skill
import com.karrel.aidiscover.R
import com.karrel.aidiscover.ext.dp
import com.karrel.aidiscover.view.epoxy.discover.DiscoverImageController
import com.karrel.aidiscover.view.epoxy.selected.SelectedImageController
import com.karrel.aidiscover.view.item.DiscoverRecommendItem
import kotlinx.android.synthetic.main.fragment_ai_discover.*

class AiDiscoverFragment : Fragment() {

    private val selectedImageController =
        SelectedImageController()
    private val discoverImageController by lazy {
        DiscoverImageController(context!!)
    }
    private val discoverLayoutManager =
        GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)

    private val windowWidth: Int
        get() = run {
            val metrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay.getMetrics(metrics)
            return metrics.widthPixels
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ai_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSelectedProfileRecyclerView()
        setupAiDiscoverRecyclerView()

        btnHistory.setOnClickListener {
            startDiscoverRecyclerViewAnim()
        }
    }

    private fun setupAiDiscoverRecyclerView() {
        ervAiDiscover.apply {
            layoutManager = discoverLayoutManager

            setController(discoverImageController)
        }

        startDiscoverRecyclerViewAnim()

        discoverImageController.setData(createDiscoverList())
    }

    private fun createDiscoverList(): List<DiscoverRecommendItem>? {
        val list = arrayListOf<DiscoverRecommendItem>()
        list.add((DiscoverRecommendItem(R.drawable.woman1, "Annbel, 20")))
        list.add((DiscoverRecommendItem(R.drawable.woman2, "Annbel, 20")))
        list.add((DiscoverRecommendItem(R.drawable.woman3, "Annbel, 20")))
        list.add((DiscoverRecommendItem(R.drawable.woman4, "Annbel, 20")))
        list.add((DiscoverRecommendItem(R.drawable.woman5, "Annbel, 20")))
        list.add((DiscoverRecommendItem(R.drawable.woman6, "Annbel, 20")))
        list.add((DiscoverRecommendItem(R.drawable.woman7, "Annbel, 20")))
        list.add((DiscoverRecommendItem(R.drawable.woman8, "Annbel, 20")))
        list.add((DiscoverRecommendItem(R.drawable.woman9, "Annbel, 20")))
        list.add((DiscoverRecommendItem(R.drawable.woman10, "Annbel, 20")))
        list.add((DiscoverRecommendItem(R.drawable.woman11, "Annbel, 20")))
        list.add((DiscoverRecommendItem(R.drawable.woman12, "Annbel, 20")))

        return list
    }

    private fun startDiscoverRecyclerViewAnim() {

        ObjectAnimator.ofFloat(0f, 1f).apply {
            interpolator = LinearInterpolator()
            duration = 300L
            startDelay = 1000L

            addUpdateListener {
                val animatedValue = it.animatedValue as Float

                val fraction = Skill.CubicEaseOut.getMethod(animatedValue)
                    .evaluate(it.animatedFraction, 1f, 0f)
                ervAiDiscover.translationX = windowWidth * fraction
            }

            doOnStart {
                ervAiDiscover.visibility = View.VISIBLE
            }

            doOnEnd {
                startLottieAnimation()
            }
        }.start()
    }

    private fun startLottieAnimation() {
        // todo play lottie

        ervAiDiscover.smoothScrollBy(windowWidth - 33.dp, 0) // after lottie animation
    }

    private fun setupSelectedProfileRecyclerView() {
        ervSelectedProfile.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setController(selectedImageController)
        }

        selectedImageController.setData(emptyList())
    }
}