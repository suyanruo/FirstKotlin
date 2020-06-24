package com.example.firstkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.ActivityDataBindBinding
import com.example.firstkotlin.model.ObservableUser
import com.example.firstkotlin.model.Person
import com.example.firstkotlin.model.User

class DataBindActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDataBindBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_data_bind)
        binding.user = User("Z", "Jay")
        val observableUser = ObservableUser()
        observableUser.firstName = "suyanruo"
        observableUser.lastName = "ting"
        binding.obUser = observableUser
        binding.person = Person(
            ObservableField("zj"),
            ObservableInt(18),
            R.drawable.shopping
        )
    }

}
