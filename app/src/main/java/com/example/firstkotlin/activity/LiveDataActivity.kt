package com.example.firstkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.ActivityLiveDataBinding
import com.example.firstkotlin.viewModel.NameViewModel

class LiveDataActivity : AppCompatActivity() {
    private lateinit var model: NameViewModel
    private lateinit var binding: ActivityLiveDataBinding
    private lateinit var tvName: TextView
    private lateinit var btnSetData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_live_data)
        tvName = binding.tvName
        btnSetData = binding.btnSetLiveData

        model = ViewModelProvider(this).get(NameViewModel::class.java)

        val nameObserver = Observer<String> {
            newName -> tvName.text = newName
        }
        model.currentName.observe(this, nameObserver)

        btnSetData.setOnClickListener {
            model.currentName.value = "John Doe"
        }
    }
}
