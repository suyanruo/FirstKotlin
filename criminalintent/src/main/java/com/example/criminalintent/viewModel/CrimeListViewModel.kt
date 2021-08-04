package com.example.criminalintent.viewModel

import androidx.lifecycle.ViewModel
import com.example.criminalintent.model.Crime

/**
 * Created on 2021/8/4.
 *
 */
class CrimeListViewModel : ViewModel() {
    val crimes = mutableListOf<Crime>()

    init {
        for (i in 0 until 100) {
            crimes.add(Crime(title = "Crime $i", isSolved = i % 2 == 0))
        }
    }
}