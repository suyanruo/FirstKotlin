package com.example.firstkotlin.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.firstkotlin.BR

/**
 * Created on 2020/6/8.
 *
 */
class ObservableUser: BaseObservable() {
    @get:Bindable
    var firstName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.firstName)
        }

    @get:Bindable
    var lastName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastName)
        }
}