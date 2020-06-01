package com.example.firstkotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.FragmentDataBindBinding

class DataBindFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        FragmentDataBindBinding.inflate(layoutInflater, container, false).root
//        DataBindingUtil.inflate(layoutInflater, R.layout.fragment_data_bind, container, false)
        return inflater.inflate(R.layout.fragment_data_bind, container, false)
    }
}
