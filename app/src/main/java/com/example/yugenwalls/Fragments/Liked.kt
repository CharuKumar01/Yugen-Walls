package com.example.yugenwalls.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.yugenwalls.R
import com.example.yugenwalls.databinding.FragmentLikedBinding

class Liked : Fragment() {
    private lateinit var bind: FragmentLikedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_liked, container, false)
        return bind.root
    }
}