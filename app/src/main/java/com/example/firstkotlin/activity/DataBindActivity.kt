package com.example.firstkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.ActivityDataBindBinding
import com.example.firstkotlin.viewModel.User

class DataBindActivity : AppCompatActivity() {
    private var age = 25

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDataBindBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_data_bind)
        binding.user = User("Z", "Jay")
    }
}
