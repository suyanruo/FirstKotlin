package com.example.criminalintent.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.criminalintent.CrimeRepository
import com.example.criminalintent.model.Crime
import java.io.File
import java.util.*

/**
 * Created on 2021/8/5.
 *
 */
class CrimeDetailsViewModel : ViewModel() {
    private val repository = CrimeRepository.get()
    private val crimeIdLiveData = MutableLiveData<UUID>()

    val crimeLiveData: LiveData<Crime> = Transformations.switchMap(crimeIdLiveData) { uuid ->
        repository.getCrime(uuid)
    }

    fun loadCrime(uuid: UUID) {
        crimeIdLiveData.value = uuid
    }

    fun saveCrime(crime: Crime) {
        repository.updateCrime(crime)
    }

    fun getPhotoFile(crime: Crime): File = repository.getPhotoFile(crime)
}