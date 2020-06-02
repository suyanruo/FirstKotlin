package com.example.firstkotlin.activity

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.ActivityRollBinding
import com.example.firstkotlin.receiver.TimeReceiver
import kotlinx.android.synthetic.main.activity_roll.*
import java.util.*

class RollActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRollBinding
    private lateinit var ivDice: ImageView
    private lateinit var btnRoll: Button
    private var timeReceiver: TimeReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 视图绑定 ref：https://developer.android.com/topic/libraries/view-binding
        binding = ActivityRollBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ivDice = binding.ivDice
        btnRoll = binding.btnRoll
        btnRoll.setOnClickListener {
            rollNumber()
        }

        // 通过"import kotlinx.android.synthetic.main.布局文件名称.*"导入布局文件同样可以实现布局文件中控件的使用
        btn_roll.text = "helloWorld"
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

    override fun onStart() {
        super.onStart()
        timeReceiver = TimeReceiver()
        val filter = IntentFilter(Intent.ACTION_TIME_TICK)
        registerReceiver(timeReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(timeReceiver)
    }
}
