package com.karrel.aidiscover.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.karrel.aidiscover.R
import com.karrel.aidiscover.view.epoxy.DiscoverImageController
import com.karrel.aidiscover.view.epoxy.SelectedImageController
import kotlinx.android.synthetic.main.fragment_ai_discover.*

class AiDiscoverFragment : Fragment() {

    private val selectedImageController = SelectedImageController()

    private val discoverImageController = DiscoverImageController()


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
    }

    private fun setupAiDiscoverRecyclerView() {
        ervAiDiscover.apply {
            layoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
            setController(discoverImageController)
        }

        discoverImageController.setData(emptyList())
    }

    private fun setupSelectedProfileRecyclerView() {
        ervSelectedProfile.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setController(selectedImageController)
        }

        selectedImageController.setData(emptyList())
    }
}