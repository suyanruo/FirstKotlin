package com.example.firstkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created on 2020/6/8.
 *
 */
class NameViewModel: ViewModel() {

    // Create a LiveData with a Stringf
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}