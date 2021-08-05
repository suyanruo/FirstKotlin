package com.example.criminalintent.viewModel

import androidx.lifecycle.ViewModel
import com.example.criminalintent.CrimeRepository

/**
 * Created on 2021/8/4.
 *
 */
class CrimeListViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData = crimeRepository.getCrimes()
}