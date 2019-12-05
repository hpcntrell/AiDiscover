package com.karrel.aidiscover.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karrel.aidiscover.R
import com.karrel.aidiscover.ext.onDestroy
import com.karrel.aidiscover.viewmodel.AiMixViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_ai_mix.*

class AiMixActivity : AppCompatActivity() {

    private val viewModel = AiMixViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_mix)

        setupViewModel()
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
            .subscribe({
                list ->
                sdvCandidate1.setActualImageResource(list[0])
                sdvCandidate2.setActualImageResource(list[1])
                sdvCandidate3.setActualImageResource(list[2])
                sdvCandidate4.setActualImageResource(list[3])
                sdvCandidate5.setActualImageResource(list[4])
            }, {

            })
    }
}
