package com.example.yugenwalls.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.yugenwalls.ImageAdapter
import com.example.yugenwalls.MainViewModel
import com.example.yugenwalls.R
import com.example.yugenwalls.databinding.FragmentExploreBinding

class Explore : Fragment() {
    private lateinit var bind: FragmentExploreBinding
    private lateinit var adapter: ImageAdapter
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ImageAdapter(requireContext())
        val manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        bind.apply {
            rvAnimeImages.adapter = adapter
            rvAnimeImages.layoutManager = manager
            rvAnimeImages.setHasFixedSize(true)
        }

        mainViewModel.animeImageList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        mainViewModel.errorMessage.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("charu", "$it")
            }
        }
    }
}