package com.example.criminalintent.viewModel

import androidx.lifecycle.ViewModel
import com.example.criminalintent.CrimeRepository
import com.example.criminalintent.model.Crime

/**
 * Created on 2021/8/4.
 *
 */
class CrimeListViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData = crimeRepository.getCrimes()

    fun addCrime(crime: Crime) {
        crimeRepository.addCrime(crime)
    }
}