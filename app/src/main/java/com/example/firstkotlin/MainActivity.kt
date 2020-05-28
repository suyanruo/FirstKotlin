package com.example.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.firstkotlin.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var ivDice: ImageView
    private lateinit var btnRoll: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 视图绑定 ref：https://developer.android.com/topic/libraries/view-binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ivDice = binding.ivDice
        btnRoll = binding.btnRoll
        btnRoll.setOnClickListener {
            rollNumber()
        }
    }

    private fun rollNumber() {
        val randomInt = Random().nextInt(6) + 1
        val imageResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_1
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        ivDice.setImageResource(imageResource)
    }
}
