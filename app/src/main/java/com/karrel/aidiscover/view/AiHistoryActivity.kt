package com.karrel.aidiscover.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karrel.aidiscover.R
import com.karrel.aidiscover.ext.debouncedClicks
import com.karrel.aidiscover.ext.onDestroy
import com.karrel.aidiscover.view.epoxy.history.HistoryController
import com.karrel.aidiscover.viewmodel.AiHistoryViewModel
import kotlinx.android.synthetic.main.activity_history.*

class AiHistoryActivity : AppCompatActivity() {

    private val viewmodel: AiHistoryViewModel by lazy { AiHistoryViewModel() }

    private val historyController: HistoryController by lazy {
        HistoryController().apply {
            onItemDeleteListener = { item ->

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setupButtonEvents()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
    }

    private fun setupRecyclerView() {
        ervHistory.apply {
            layoutManager =
                LinearLayoutManager(this@AiHistoryActivity, RecyclerView.HORIZONTAL, false)
            setController(historyController)
        }
    }

    private fun setupButtonEvents() {
        btnBack.debouncedClicks()
            .onDestroy(this)
            .subscribe {
                finish()
            }
    }
}
